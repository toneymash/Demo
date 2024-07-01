package com.example.investapp.ui.emailverification

import EmailVerificationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("verify-email")
    fun verifyEmail(@Body email: String): Call<EmailVerificationResponse>
}