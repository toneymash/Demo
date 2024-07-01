package com.example.investapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.investapp.LoginActivity
import com.example.investapp.R

class OtpVerificationActivity : AppCompatActivity() {
    private lateinit var otpEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otp_verification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupListeners()
    }

    private fun initViews() {
        otpEditText = findViewById(R.id.otpEditText)
        newPasswordEditText = findViewById(R.id.newPasswordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        resetButton = findViewById(R.id.resetButton)
    }

    private fun setupListeners() {
        resetButton.setOnClickListener { resetPassword() }
    }

    private fun resetPassword() {
        val otp = otpEditText.text.toString()
        val newPassword = newPasswordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        if (otp.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (newPassword != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Here you would typically make an API call to verify the OTP and reset the password
        // For this example, we'll just simulate a successful reset
        if (verifyOtpAndResetPassword(otp, newPassword)) {
            Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show()
            navigateToLogin()
        } else {
            Toast.makeText(this, "Failed to reset password. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyOtpAndResetPassword(otp: String, newPassword: String): Boolean {
        // This is where you'd typically make an API call to verify the OTP and reset the password
        // For this example, we'll just return true to simulate a successful reset
        return true
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // This closes the current activity
    }
}