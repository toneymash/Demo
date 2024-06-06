package com.example.investapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val Email: String, val Password: String)
data class LoginResponse(val token: String, val Email: String)

interface ApiService {
    @POST("api/open/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}