package com.example.investapp.ui.our_products
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/funds")
    suspend fun getProducts(): List<Product>
    @GET("product_details/{id}")
    suspend fun getProductDetails(@Path("id") productId: Int): Product

}