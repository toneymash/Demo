package com.example.investapp.ui
data class LoginResponse(
    val token: String? = null,
    val email: String? = null,
    val message: String? = null,
    val status: Int? = null
)
