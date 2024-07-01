package com.example.investapp.ui.our_products

data class Product(
    val id: Int,
    val name :String,
    val abbreviation: String,
    val fundType: String,
    val currency: String,
    val moreDetails: String
)