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
import com.example.investapp.Home
import com.example.investapp.R
import com.example.investapp.R.id.btn_invest1
import com.example.investapp.calcutor
import com.example.investapp.ui.news.News

class productsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_products)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttoninvest = findViewById<Button>(btn_invest1)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val newsList = createSampleNewsList() // Function to create sample news data
        newsAdapter = NewsAdapter(this, newsList)
        recyclerView.adapter = newsAdapter
        buttoninvest.setOnClickListener {
            val intent = Intent(this, calcutor::class.java)
            startActivity(intent)
        }
    }

    private fun createSampleNewsList(): List<News> {
        // Replace with your actual news data
        val news1 = News(
            "BONDS",
            "Bonds are fixed-income financial instruments representing a loan made by an investor to a borrower (typically corporate or governmental).\n" +
                    "Key Features:\n" +
                    "\n" +
                    "Principal (Face Value): The amount borrowed that the issuer agrees to repay the bondholder at maturity.\n" +
                    "Coupon Rate: The interest rate the bond issuer pays to the bondholder, typically annually or semi-annually.\n" +
                    "Maturity Date: The date on which the bond's principal amount is to be paid back in full.\n" +
                    "Issuer: The entity that issues the bond, such as a government, municipality, or corporation.\n" +
                    "Types of Bonds:\n" +
                    "\n" +
                    "Government Bonds: Issued by national governments, considered low-risk.\n" +
                    "Corporate Bonds: Issued by companies, usually offering higher yields due to higher risk.\n" +
                    "Municipal Bonds: Issued by states, cities, or other local government entities, often tax-exempt.\n" +
                    " Department of the Treasury.\n" +
                    "Junk Bonds: High-yield, high-risk bonds rated below investment grade.\n" +
                    "Bond Valuation:\n" +
                    "\n" +
                    "Yield: The return on the bond investment, which can be calculated as current yield (annual interest payment divided by current price) or yield to maturity (total return if held to maturity).\n" +
                    "Price: Bond prices inversely relate to interest rates; as interest rates rise, bond prices fall, and vice versa.\n" +
                    "Advantages:\n" +
                    "\n" +
                    "Predictable Income: Regular interest payments provide steady income.\n" +
                    "Safety: Especially government bonds, which are considered very safe.\n" +
                    "Diversification: Adding bonds to a portfolio can reduce overall investment risk.\n" +
                    "Disadvantages:\n" +
                    "\n" +
                    "Interest Rate Risk: Bonds lose value when interest rates rise.\n" +
                    "Credit Risk: The risk that the issuer might default on payments.\n" +
                    "Inflation Risk: Fixed interest payments may lose purchasing power over time if inflation rises.\n" +
                    "Uses of Bonds:\n" +
                    "\n" +
                    "Financing Projects: Governments and corporations use bond proceeds to fund various projects and operations.\n" +
                    "Investment Diversification: Investors use bonds to diversify their portfolios and mitigate risk.",
            "https://www.investopedia.com/articles/bonds/08/bond-market-basics.asp/"
        )
        val news2 = News(
            "Bond Markets",
            "Bonds are traded on bond markets, which can be either primary (new issues) or secondary (existing bonds). Major bond markets include the U.S. Treasury market and the corporate bond market",
            ""
        )
        val news3 = News(
            "VIEW MORE...",
            "",
            "https://www.investopedia.com/articles/bonds/08/bond-market-basics.asp"
        )
        // Add more news items as needed

        return listOf(news1, news2, news3)
    }
}
