package com.example.investapp.api

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val idNumber: String,
    val email: String,
    val password: String,
    val phoneNumber: String
)
