package com.example.investapp.ui.personaldetails

import IdPicture
import NextOfKin
import PersonalDetailsRequest
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.investapp.databinding.ActivityPersonalDetailsBinding
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.Calendar

class PersonalDetails : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalDetailsBinding
    private lateinit var apiService: ApiService

    private var frontImageUri: Uri? = null
    private var backImageUri: Uri? = null

    companion object {
        private const val PICK_FRONT_ID_IMAGE = 1
        private const val PICK_BACK_ID_IMAGE = 2
        private const val TAG = "PersonalDetails"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = RetrofitClient.createService(ApiService::class.java)

        setupImagePickers()
        setupSubmitButton()
        setupDatePicker()
    }

    private fun setupImagePickers() {
        binding.btnPickFrontId.setOnClickListener { pickImage(PICK_FRONT_ID_IMAGE) }
        binding.btnPickBackId.setOnClickListener { pickImage(PICK_BACK_ID_IMAGE) }
    }

    private fun setupSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            if (validateInputs()) {
                getPersonalDetailsFromInput()?.let { details -> submitPersonalDetails(details) }
            }
        }
    }

    private fun setupDatePicker() {
        binding.dateOfBirth.setOnClickListener { showDatePickerDialog() }
    }

    private fun pickImage(requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, requestCode)
    }

    private fun validateInputs(): Boolean {
        with(binding) {
            val fields = listOf(
                surname, middleName, firstName, dateOfBirth, idpassportNo, nationality,
                email, postalAddress, accountName, accountNumber, accountType, bankName,
                bankBranch, bankCode, mpesaNo, nextOfKinName, nextOfKinRelationship, id, telephone
            )

            fields.forEach { field ->
                if (field.text.isNullOrBlank()) {
                    field.error = "This field is required"
                    return false
                }
            }

            if (frontImageUri == null || backImageUri == null) {
                Toast.makeText(this@PersonalDetails, "Please select both front and back ID images", Toast.LENGTH_SHORT).show()
                return false
            }

            return true
        }
    }

    private fun getPersonalDetailsFromInput(): PersonalDetailsRequest? {
        with(binding) {
            return PersonalDetailsRequest(
                surname = surname.text.toString(),
                middleName = middleName.text.toString(),
                firstName = firstName.text.toString(),
                dateOfBirth = dateOfBirth.text.toString(),
                idPassportNo = idpassportNo.text.toString(),
                nationality = nationality.text.toString(),
                email = email.text.toString(),
                postalAddress = postalAddress.text.toString(),
                accountName = accountName.text.toString(),
                accountNumber = accountNumber.text.toString(),
                accountType = accountType.text.toString(),
                bankName = bankName.text.toString(),
                bankBranch = bankBranch.text.toString(),
                bankCode = bankCode.text.toString(),
                phoneNumber = telephone.text.toString(),
                idPicture = IdPicture(frontImageUri.toString(), backImageUri.toString()),
                nextOfKin = NextOfKin(
                    name = nextOfKinName.text.toString(),
                    relationship = nextOfKinRelationship.text.toString(),
                    id = id.text.toString(),
                    telephone = telephone.text.toString()
                )
            )
        }
    }

    private fun submitPersonalDetails(personalDetails: PersonalDetailsRequest) {
        binding.progressBar.visibility = View.VISIBLE

        // Convert personalDetails to JSON
        val gson = Gson()
        val personalDetailsJson = gson.toJson(personalDetails)

        // Create RequestBody from JSON
        val dataRequestBody = personalDetailsJson.toRequestBody("application/json".toMediaTypeOrNull())

        // Prepare image parts
        val frontImgPart = frontImageUri?.let { uriToMultipartBodyPart(it, "frontImg") }
        val backImgPart = backImageUri?.let { uriToMultipartBodyPart(it, "backImg") }

        if (frontImgPart == null || backImgPart == null) {
            Toast.makeText(this, "Please select both front and back ID images", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
            return
        }

        // Log request details
        Log.d(TAG, "Submitting details: $personalDetailsJson")

        val call = apiService.submitPersonalDetails(
            data = dataRequestBody,
            frontImg = frontImgPart,
            backImg = backImgPart
        )

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    Toast.makeText(this@PersonalDetails, "Details submitted successfully", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Submission successful")
                    // TODO: Navigate to next screen or finish activity
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = when {
                        errorBody != null -> "Submission failed: $errorBody"
                        response.message().isNotEmpty() -> "Submission failed: ${response.message()}"
                        else -> "Submission failed: Unknown error"
                    }
                    Toast.makeText(this@PersonalDetails, errorMessage, Toast.LENGTH_LONG).show()
                    Log.e(TAG, "Error response: ${response.code()} - $errorMessage")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                val errorMessage = "Network error: ${t.message}"
                Toast.makeText(this@PersonalDetails, errorMessage, Toast.LENGTH_LONG).show()
                Log.e(TAG, "Network error", t)
            }
        })
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, day ->
                binding.dateOfBirth.setText("$day/${month + 1}/$year")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            if (selectedImageUri != null) {
                when (requestCode) {
                    PICK_FRONT_ID_IMAGE -> {
                        binding.ivFrontIdPicture.setImageURI(selectedImageUri)
                        binding.ivFrontIdPicture.visibility = View.VISIBLE
                        frontImageUri = selectedImageUri
                        binding.tvIdPictureFrontPath.text = selectedImageUri.toString()
                    }
                    PICK_BACK_ID_IMAGE -> {
                        binding.ivBackIdPicture.setImageURI(selectedImageUri)
                        binding.ivBackIdPicture.visibility = View.VISIBLE
                        backImageUri = selectedImageUri
                        binding.tvIdPictureBackPath.text = selectedImageUri.toString()
                    }
                }
            }
        }
    }

    private fun uriToMultipartBodyPart(uri: Uri, partName: String): MultipartBody.Part {
        val inputStream = contentResolver.openInputStream(uri)
        val file = File(cacheDir, "temp_image_${System.currentTimeMillis()}")
        file.outputStream().use { outputStream ->
            inputStream?.copyTo(outputStream)
        }
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }
}