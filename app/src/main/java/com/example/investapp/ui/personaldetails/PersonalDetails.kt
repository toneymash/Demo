package com.example.investapp.ui.personaldetails

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.investapp.R

class PersonalDetails : AppCompatActivity() {

    private lateinit var surnameEditText: EditText
    private lateinit var middleNameEditText: EditText
    private lateinit var firstNameEditText: EditText
    private lateinit var dateOfBirthEditText: EditText
    private lateinit var nationalityEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var postalAddressEditText: EditText
    private lateinit var accountNameEditText: EditText
    private lateinit var accountNumberEditText: EditText
    private lateinit var accountTypeEditText: EditText
    private lateinit var bankBranchEditText: EditText
    private lateinit var bankCodeEditText: EditText
    private lateinit var mpesaNoEditText: EditText
    private lateinit var relationshipEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var telephoneEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_personal_details)

        initializeViews()
        setupSubmitButton()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.PersonalDetailsActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeViews() {
        surnameEditText = findViewById(R.id.surname)
        middleNameEditText = findViewById(R.id.middleName)
        firstNameEditText = findViewById(R.id.firstName)
        dateOfBirthEditText = findViewById(R.id.dateOfBirth)
        nationalityEditText = findViewById(R.id.nationality)
        emailEditText = findViewById(R.id.email)
        postalAddressEditText = findViewById(R.id.postalAddress)
        accountNameEditText = findViewById(R.id.accountName)
        accountNumberEditText = findViewById(R.id.accountNumber)
        accountTypeEditText = findViewById(R.id.accountType)
        bankBranchEditText = findViewById(R.id.bankBranch)
        bankCodeEditText = findViewById(R.id.bankCode)
        mpesaNoEditText = findViewById(R.id.mpesaNo)
        relationshipEditText = findViewById(R.id.Relationship)
        idEditText = findViewById(R.id.id)
        telephoneEditText = findViewById(R.id.telephone)
        submitButton = findViewById(R.id.btn_submit)
    }

    private fun setupSubmitButton() {
        submitButton.setOnClickListener {
            if (validateInputs()) {
                // TODO: Process the form data (e.g., save to database, send to server)
                Toast.makeText(this, "Form submitted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(): Boolean {
        // Check if all fields are filled
        return listOf(
            surnameEditText, middleNameEditText, firstNameEditText, dateOfBirthEditText,
            nationalityEditText, emailEditText, postalAddressEditText, accountNameEditText,
            accountNumberEditText, accountTypeEditText, bankBranchEditText, bankCodeEditText,
            mpesaNoEditText, relationshipEditText, idEditText, telephoneEditText
        ).all { it.text.isNotBlank() }
    }
}