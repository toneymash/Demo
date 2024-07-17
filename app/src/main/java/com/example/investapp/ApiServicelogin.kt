package com.example.investapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val token: String,
    val email: String,
    val userId:Int,
    val phoneNumber: String
)


interface ApiService {
    @POST("api/open/registration/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}