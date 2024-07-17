package com.example.investapp.ui.emailverification

import EmailVerificationResponse
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.investapp.Home
import com.example.investapp.R
import com.example.investapp.ui.OtpVerificationActivity
import com.example.investapp.ui.Reset_password
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailVerificationActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_emailverification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupListeners()
    }

    private fun initViews() {
        emailEditText = findViewById(R.id.emailEditText)
        submitButton = findViewById(R.id.submitButton)
    }

    private fun setupListeners() {
        submitButton.setOnClickListener { verifyEmail() }

    }

    private fun verifyEmail() {
        val email = emailEditText.text.toString()
        if (email.isNotEmpty()) {
            // Disable the button while checking
            submitButton.isEnabled = false
            checkEmailWithApi(email)
        } else {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkEmailWithApi(email: String) {
        // Using Retrofit for API call
        val apiService = RetrofitClient.getApiService()
        val call = apiService.verifyEmail(email)

        call.enqueue(object : Callback<EmailVerificationResponse> {
            override fun onResponse(call: Call<EmailVerificationResponse>, response: Response<EmailVerificationResponse>) {
                submitButton.isEnabled = true
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result?.isValid == true) {
                        // Email is valid, proceed to OTP activity
                        startOtpActivity(email)
                    } else {
                        Toast.makeText(this@EmailVerificationActivity, "Invalid email", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@EmailVerificationActivity, "Error verifying email", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EmailVerificationResponse>, t: Throwable) {
                submitButton.isEnabled = true
                Toast.makeText(this@EmailVerificationActivity, "Network error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun startOtpActivity(email: String) {
        val intent = Intent(this, OtpVerificationActivity::class.java)
        intent.putExtra("EMAIL", email)
        startActivity(intent)
    }
}