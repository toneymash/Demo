package com.example.investapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.investapp.R

class moneymarketcalculator : AppCompatActivity() {
    private lateinit var initialAmountEditText: EditText
    private lateinit var monthlyTopUpEditText: EditText
    private lateinit var interestRateEditText: EditText
    private lateinit var durationYearsEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_moneymarketcalculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initialAmountEditText = findViewById(R.id.edit_initial_amount)
        monthlyTopUpEditText = findViewById(R.id.edit_monthly_topup)
        interestRateEditText = findViewById(R.id.edit_interest_rate)
        durationYearsEditText = findViewById(R.id.edit_duration_years)
        calculateButton = findViewById(R.id.btn_calculate)
        resultTextView = findViewById(R.id.text_result)

        calculateButton.setOnClickListener {
            calculateAndDisplayResult()
        }
    }

    private fun calculateAndDisplayResult() {
        val initialAmount = initialAmountEditText.text.toString().toDoubleOrNull() ?: 0.0
        val monthlyTopUp = monthlyTopUpEditText.text.toString().toDoubleOrNull() ?: 0.0
        val interestRate = interestRateEditText.text.toString().toDoubleOrNull() ?: 0.0
        val durationYears = durationYearsEditText.text.toString().toIntOrNull() ?: 0

        val annualInterestRate = interestRate / 100.0
        val dailyInterestRate = annualInterestRate / 365.0
        val periodsPerYear = 365

        var futureValue = initialAmount

        for (year in 1..durationYears) {
            for (day in 1..365) {
                futureValue *= (1 + dailyInterestRate)
                if (day % (365 / periodsPerYear) == 0) {
                    futureValue += monthlyTopUp
                }
            }
        }

        resultTextView.text = getString(R.string.result_format, futureValue)
    }
}