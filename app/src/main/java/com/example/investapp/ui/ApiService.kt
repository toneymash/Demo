package com.example.investapp.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/open/registration/register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>
}
