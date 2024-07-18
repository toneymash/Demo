package com.example.investapp.ui.our_products
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("/api/open/funds")
    suspend fun getProducts(
        @Header("Authorization") authToken: String
    ): List<Product>

    @GET("product_details/{id}")
    suspend fun getProductDetails(
        @Path("id") productId: Int,
        @Header("Authorization") authToken: String
    ): Product
}