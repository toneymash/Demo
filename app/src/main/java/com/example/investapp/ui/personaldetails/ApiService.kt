package com.example.investapp.ui.personaldetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/fetchusers")
    suspend fun submitPersonalDetails(@Body personalDetails: PersonalDetails): Response<SubmitResponse>
}
