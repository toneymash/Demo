package com.example.investapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.common.util.SharedPreferencesUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    private lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Apply window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tokenManager = TokenManager(this)

        val editpassword = findViewById<EditText>(R.id.editTextpassword)
        val editemail = findViewById<EditText>(R.id.editemail)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val forgotpass=findViewById<TextView>(R.id.forgot)

        forgotpass.setOnClickListener{
            val intent = Intent(this, Home::class.java)
            startActivity(intent)

        }



        buttonLogin.setOnClickListener {
            val email = editemail.text.toString().trim()
            val password = editpassword.text.toString().trim()

            //val intent = Intent(this, Home::class.java)
           // startActivity(intent)
            if (isValidEmail(email) && isValidPassword(password)) {
                // Perform login using Retrofit
                performLogin(email, password)
            } else {
                if (!isValidEmail(email)) {
                    Toast.makeText(this, "Please enter a valid Email", Toast.LENGTH_SHORT).show()
                } else if (!isValidPassword(password)) {
                    Toast.makeText(this, "Please enter a valid Password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        // Regular expression for validating email
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    private fun isValidPassword(password: String): Boolean {
        // Password should not be empty and should be at least 6 characters long (you can adjust this)
        return password.isNotEmpty() && password.length >= 3
    }

    private fun performLogin(email: String, password: String) {
        val apiService = RetrofitClientlogin.instance.create(ApiService::class.java)
        val call = apiService.login(LoginRequest(email, password))

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        // Log the raw response
                        Log.d("LoginActivity", "LoginResponse: ${response.body()}")

                        // Check if the token is null
                        if (loginResponse.token != null) {

                            tokenManager.saveToken(loginResponse.token, loginResponse.token)
                            val token = loginResponse.token

                            Log.e("TAG", "${token}")

                            Constants.TOKEN = token



                            println("Token saved")
                            println("User ID: ${loginResponse.userId}")
                            Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, Home::class.java)
                            intent.putExtra("TOKEN", loginResponse.token)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Login failed: Token is null", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed: Response body is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
