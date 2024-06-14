package com.example.investapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.DatePickerDialog
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView

import java.util.*
import kotlin.math.pow

class calcutor : AppCompatActivity() {
    private lateinit var valueDateEditText: EditText
    private lateinit var investmentTermEditText: EditText
    private lateinit var faceValueEditText: EditText
    private lateinit var couponRateEditText: EditText
    private lateinit var interestRateEditText: EditText
    private lateinit var resultTextView: TextView

    private val kraWithholdingTaxRate = 15.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calcutor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        valueDateEditText = findViewById(R.id.valueDateEditText)
        investmentTermEditText = findViewById(R.id.investmentTermEditText)
        faceValueEditText = findViewById(R.id.faceValueEditText)
        couponRateEditText = findViewById(R.id.couponRateEditText)
        interestRateEditText = findViewById(R.id.interestRateEditText)
        resultTextView = findViewById(R.id.resultTextView)
        val calculateButton: Button = findViewById(R.id.calculateButton)

        valueDateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        calculateButton.setOnClickListener {
            calculateBondValue()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            valueDateEditText.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun calculateBondValue() {
        val faceValue = faceValueEditText.text.toString().toDouble()
        val couponRate = couponRateEditText.text.toString().toDouble() / 100
        val interestRate = interestRateEditText.text.toString().toDouble() / 100
        val investmentTerm = investmentTermEditText.text.toString().toInt()

        val annualCouponPayment = faceValue * couponRate
        var totalCouponPayments = 0.0
        var discountedCouponSum = 0.0

        for (year in 1..investmentTerm) {
            totalCouponPayments += annualCouponPayment
            discountedCouponSum += annualCouponPayment / (1 + interestRate).pow(year)
        }

        val totalWithholdingTax = totalCouponPayments * kraWithholdingTaxRate / 100

        val presentValueFaceValue = faceValue / (1 + interestRate).pow(investmentTerm)
        val totalValue = discountedCouponSum + presentValueFaceValue - totalWithholdingTax

        resultTextView.text = "Total Value: Ksh %.2f".format(totalValue)
    }
}