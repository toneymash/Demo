package com.example.investapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bondsnews.NewsAdapter
import com.example.investapp.R
import com.example.investapp.calcutor
import com.example.investapp.ui.news.News

class MoneyMarketActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_money_market) // Make sure this is the correct layout

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonmoney = findViewById<Button>(R.id.btn_invest2)
        buttonmoney.setOnClickListener {
            val intent = Intent(this, moneymarketcalculator::class.java)
            startActivity(intent)
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
            "MONEY MARKET",
            "Money Market: A segment of the financial market where short-term borrowing, lending, buying, and selling of financial instruments occur. It deals in highly liquid and short-term instruments.\n" +
                    "Characteristics\n" +
                    "Short-term: Typically, instruments have maturities of one year or less.\n" +
                    "High Liquidity: Instruments are easily converted to cash.\n" +
                    "Low Risk: Generally considered low risk due to short maturities and high credit quality.\n" +
                    "Key Instruments\n" +
                    "Treasury Bills (T-Bills): Short-term government securities with maturities ranging from a few days to 52 weeks.\n" +
                    "Commercial Paper: Unsecured promissory notes issued by companies to meet short-term liabilities.\n" +
                    "Certificates of Deposit (CDs): Time deposits offered by banks with specific maturity dates and interest rates.\n" +
                    "Repurchase Agreements (Repos): Short-term loans where securities are sold with an agreement to repurchase them at a higher price.\n" +
                    "Banker's Acceptances: Short-term credit investments created by a non-financial firm and guaranteed by a bank.\n" +
                    "Participants\n" +
                    "Government: Issues T-bills to manage liquidity and monetary policy.\n" +
                    "Banks: Participate by issuing CDs and engaging in repos.\n" +
                    "Corporations: Issue commercial paper to fund short-term needs.\n" +
                    "Investors: Include mutual funds, financial institutions, and individual investors seeking low-risk investments....",
            "/"
        )
        val news2 = News(
            "Key Characteristics:\n" +
                    "\n",
            "Liquidity: Money market instruments are highly liquid, meaning they can be quickly converted into cash.\n" +
                    "Safety: Generally considered low-risk due to their short maturities and the creditworthiness of the issuers.\n" +
                    "Low Returns: Offer lower returns compared to long-term securities due to lower risk and short duration.\n" +
                    "High Denomination: Often issued in large denominations, making them more accessible to institutional investors.",
            "https://example.com/news2"
        )
        val news3 = News(
            "VIEW MORE",
            ".",
            "https://www.investopedia.com/investing/introduction-to-money-market-mutual-funds/#:"
        )
        // Add more news items as needed

        return listOf(news1, news2, news3)

    }


}

