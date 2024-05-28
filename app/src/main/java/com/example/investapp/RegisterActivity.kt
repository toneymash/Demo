package com.example.investapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val editTextIDNumber = findViewById<EditText>(R.id.edittextIDNUMBER)
        val editTextEmail = findViewById<EditText>(R.id.edittextemail)
        val editTextPassword = findViewById<EditText>(R.id.edittextpassword)
        val buttonRegister = findViewById<Button>(R.id.editbtnregister)

        buttonRegister.setOnClickListener {
            val firstName = editTextFirstName.text.toString()
            val lastName = editTextLastName.text.toString()
            val idNumber = editTextIDNumber.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && idNumber.isNotEmpty() &&
                email.isNotEmpty() && password.isNotEmpty()
            ) {
                // Perform registration logic here
                // For now, just show a toast message
                Toast.makeText(this, "Register successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
            }
        }






    }
}