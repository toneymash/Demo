package com.example.investapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.investapp.api.RegisterRequest
import com.example.investapp.api.RegisterResponse
import com.example.investapp.api.RetrofitClient
import com.example.investapp.ui.personaldetails.PersonalDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextFirstName = findViewById<EditText>(R.id.edittextfirstname)
        val editTextLastName = findViewById<EditText>(R.id.edittextlastname)
        val editTextEmail = findViewById<EditText>(R.id.edittextemail)
        val editTextPhoneNumber = findViewById<EditText>(R.id.edittextphonenumber)
        val editTextIDNumber = findViewById<EditText>(R.id.edittextidNumber)
        val editTextPassword = findViewById<EditText>(R.id.edittextpassword)
        val buttonRegister = findViewById<Button>(R.id.btn_register)

        buttonRegister.setOnClickListener {
            val firstName = editTextFirstName.text.toString()
            val lastName = editTextLastName.text.toString()
            val idNumber = editTextIDNumber.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val phoneNumber = editTextPhoneNumber.text.toString()
         //  val intent = Intent(this@RegisterActivity, PersonalDetails::class.java)
           //  startActivity(intent)

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && idNumber.isNotEmpty() &&
                email.isNotEmpty() && password.isNotEmpty() && phoneNumber.isNotEmpty()
            ) {
                val request = RegisterRequest(firstName, lastName, idNumber, email, password, phoneNumber)

                RetrofitClient.apiService.registerUser(request).enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Register successful", Toast.LENGTH_SHORT).show()
                           val intent = Intent(this@RegisterActivity, PersonalDetails::class.java)
                           startActivity(intent)
                        } else {

                            Toast.makeText(this@RegisterActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        print(t);

                        Toast.makeText(this@RegisterActivity, "API call failed: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
