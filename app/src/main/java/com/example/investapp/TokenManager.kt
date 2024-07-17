package com.example.investapp

import android.content.Context
import android.content.SharedPreferences

class TokenManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("your_app_prefs", Context.MODE_PRIVATE)
    }

    fun saveToken(token: String, userId: String) {
        with(sharedPreferences.edit()) {
            putString("token", token)
            putString("userId", userId)
            apply()
        }
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }

    fun clearToken() {
        with(sharedPreferences.edit()) {
            remove("token")
            remove("userId")
            apply()
        }
    }
}
