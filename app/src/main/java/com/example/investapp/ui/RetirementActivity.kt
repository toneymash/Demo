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

class RetirementActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_retirement)
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
            "RETiREMENT PLAN",
            "Definition:\n" +
                    "\n" +
                    "A retirement plan is a financial strategy designed to help individuals save and invest money to secure their financial future after they retire from active work life.\n" +
                    "Types of Retirement Plans:\n" +
                    "\n" +
                    "Employer-Sponsored Plans:\n" +
                    "\n" +
                    "401(k) Plan:\n" +
                    "Offered by employers, allows employees to contribute a portion of their wages on a pre-tax or post-tax (Roth) basis.\n" +
                    "Employers may match contributions.\n" +
                    "403(b) Plan:\n" +
                    "Similar to 401(k), but available to employees of non-profit organizations, public schools, and certain ministers.\n" +
                    "457 Plan:\n" +
                    "Available to state and local government employees, as well as some non-profit organizations.\n" +
                    "Individual Retirement Accounts (IRAs):\n" +
                    "\n" +
                    "Traditional IRA:\n" +
                    "Contributions may be tax-deductible, and earnings grow tax-deferred until withdrawal at retirement.\n" +
                    "Withdrawals are taxed as regular income.\n" +
                    "Roth IRA:\n" +
                    "Contributions are made with after-tax dollars, and earnings grow tax-free.\n" +
                    "Qualified withdrawals are tax-free.\n" +
                    "Pension Plans:\n" +
                    "\n" +
                    "Defined Benefit Plan:\n" +
                    "Provides a fixed, pre-established benefit for employees at retirement.\n" +
                    "Benefits are typically based on factors such as salary history and length of employment.\n" +
                    "Defined Contribution Plan:\n" +
                    "Benefits are based on the contributions made and the performance of the investments chosen.\n" +
                    "Examples include 401(k) and 403(b) plans.\n" +
                    "Self-Employed Plans:\n" +
                    "\n" +
                    "SEP IRA:\n" +
                    "Simplified Employee Pension, allowing self-employed individuals to make tax-deductible contributions.\n" +
                    "Solo 401(k):\n" +
                    "Similar to a regular 401(k) but designed for self-employed individuals or small business owners with no employees.\n" +
                    "Key Concepts:\n" +
                    "\n" +
                    "Contribution Limits: Each plan has annual contribution limits set by the IRS.\n" +
                    "Tax Advantages: Contributions to certain plans can reduce taxable income, and earnings grow tax-deferred or tax-free.\n" +
                    "Employer Matching: Some plans offer employer matching contributions, effectively \"free money\" for the employee.\n" +
                    "Vesting: The process by which an employee earns the right to receive full benefits from employer contributions.\n" +
                    "Required Minimum Distributions (RMDs): The minimum amount that must be withdrawn from certain retirement accounts starting at age 73 (as of 2023).\n" +
                    "Advantages:\n" +
                    "\n" +
                    "Tax Benefits: Contributions to retirement accounts can reduce taxable income, and investment growth may be tax-deferred or tax-free.\n" +
                    "Compound Interest: Over time, the interest earned on investments can significantly increase the value of retirement savings.\n" +
                    "Financial Security: Provides a source of income during retirement, helping maintain the standard of living.\n" +
                    "Disadvantages:\n" +
                    "\n" +
                    "Withdrawal Penalties: Early withdrawals (before age 59½) from most retirement accounts may incur penalties and taxes.\n" +
                    "Investment Risk: The value of investments can fluctuate, affecting the amount of money available at retirement.\n" +
                    "Inflation Risk: The purchasing power of saved funds may decrease over time due to inflation.",
            "https://www.oldmutual.co.ke/investment/investment-portal/"
        )
        val news2 = News(
            "Retirement Planning Strategies:",
            "Start Early: Begin saving for retirement as soon as possible to take advantage of compound interest.\n" +
                    "Diversify Investments: Spread investments across different asset classes to reduce risk.\n" +
                    "Regular Contributions: Make consistent contributions to retirement accounts.\n" +
                    "Monitor and Adjust: Regularly review and adjust retirement plans based on changes in income, expenses, and retirement goals.",
            "https://example.com/news2"
        )
        val news3 = News(
            "VIEW MORE.....",
            "",
            "https://www.fidelity.com/retirement-planning/plan-for-retirement"
        )
        // Add more news items as needed

        return listOf(news1, news2, news3)
    }
}
