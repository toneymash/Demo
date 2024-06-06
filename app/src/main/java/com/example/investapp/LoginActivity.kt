package com.example.investapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val editPassword = findViewById<EditText>(R.id.editTextPassword)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val buttonRegister=  findViewById<Button>(R.id.buttonRegister)



        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val username = editEmail .text.toString()
            val password = editPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
//                performLogin(username, password)
                val intent = Intent(this, Home::class.java)
                startActivity(intent)




            } else {
                Toast.makeText(this, "Please enter Email and password", Toast.LENGTH_SHORT).show()
            }
        }


        buttonRegister.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)




        }
    }




    private fun performLogin(Email: String, password: String) {
      val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val call = apiService.login(LoginRequest(Email, password))

       call.enqueue(object : Callback<LoginResponse> {
           override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
               if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()
                    // Navigate to another activity if needed
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("TOKEN", loginResponse?.token)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}