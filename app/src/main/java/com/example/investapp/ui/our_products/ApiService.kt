package com.example.investapp.ui.our_products
import retrofit2.http.GET

interface ApiService {
    @GET("/api/funds")
    suspend fun getProducts(): List<Product>
}