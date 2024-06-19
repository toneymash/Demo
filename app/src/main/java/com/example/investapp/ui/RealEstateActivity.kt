package com.example.investapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bondsnews.NewsAdapter
import com.example.investapp.R
import com.example.investapp.ui.news.News

class RealEstateActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_real_estate)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val newsList = createSampleNewsList() // Function to create sample news data
        newsAdapter = NewsAdapter(this, newsList)
        recyclerView.adapter = newsAdapter
    }

    private fun createSampleNewsList(): List<News> {
        // Replace with your actual news data
        val news1 = News(
            "REAL ESTATE",
            "Real estate refers to land and any permanent structures attached to it, such as buildings or homes.\n" +
                    "Key Types of Real Estate:\n" +
                    "\n" +
                    "Residential Real Estate:\n" +
                    "\n" +
                    "Properties intended for human habitation, such as houses, apartments, and condominiums.\n" +
                    "Examples: Single-family homes, duplexes, townhouses.\n" +
                    "Commercial Real Estate:\n" +
                    "\n" +
                    "Properties used for business purposes.\n" +
                    "Examples: Office buildings, retail stores, hotels, shopping centers.\n" +
                    "Industrial Real Estate:\n" +
                    "\n" +
                    "Properties used for manufacturing, production, distribution, and storage.\n" +
                    "Examples: Factories, warehouses, research facilities.\n" +
                    "Land:\n" +
                    "\n" +
                    "Vacant land or farmland.\n" +
                    "Can be developed for various purposes, including agriculture, residential, or commercial use.\n" +
                    "Special Purpose Real Estate:\n" +
                    "\n" +
                    "Properties that serve a specific function.\n" +
                    "Examples: Schools, churches, cemeteries, government buildings.\n" +
                    "Key Concepts:\n" +
                    "\n" +
                    "Location: The most crucial factor affecting real estate value. Desirability and accessibility influence property prices.\n" +
                    "Supply and Demand: Determines market conditions. High demand and low supply drive prices up, and vice versa.\n" +
                    "Market Value: The estimated amount for which a property should exchange on the date of valuation between a willing buyer and a willing seller.\n" +
                    "Appreciation: Increase in property value over time due to factors like location improvements, economic conditions, or market demand.\n" +
                    "Depreciation: Decrease in property value due to wear and tear, obsolescence, or market downturns.\n" +
                    "Leverage: Using borrowed capital (mortgage) to purchase and increase the potential return on investment.\n" +
                    "Equity: The difference between the market value of a property and the amount owed on any mortgages.",
            ""
        )
        val news2 = News(
            "Real Estate Investment Strategies",
            "Buy and Hold:\n" +
                    "\n" +
                    "Purchase properties to rent out and hold for long-term appreciation.\n" +
                    "Generates rental income and potential capital gains.\n" +
                    "Flipping:\n" +
                    "\n" +
                    "Buying properties below market value, renovating, and selling for a profit.\n" +
                    "Focus on short-term gains.\n" +
                    "Real Estate Investment Trusts (REITs):\n" +
                    "\n" +
                    "Investing in publicly traded companies that own, operate, or finance income-producing real estate.\n" +
                    "Provides exposure to real estate without direct property ownership.\n" +
                    "Real Estate Crowdfunding:\n" +
                    "\n" +
                    "Pooling funds with other investors to invest in real estate projects.\n" +
                    "Lower entry costs compared to direct ownership.\n" +
                    "Real Estate Markets:\n" +
                    "\n" +
                    "Local and national markets can vary greatly in terms of property values, demand, and economic conditions.\n" +
                    "Understanding market trends, such as employment rates, interest rates, and demographic shifts, is crucial for making informed investment decisions..",
            "https://example.com/news2"
        )
        val news3 = News(
            "VIEW MORE.....",
            "",
            "https://www.linkedin.com/pulse/navigating-balance-real-estate-agents-guide-success-well-being-deam-jk7rc"
        )
        // Add more news items as needed

        return listOf(news1, news2, news3)
    }
}
