package com.example.foodieapp.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class FoodhubSession(context: Context) {
    val sharedPres: SharedPreferences = context.getSharedPreferences("foodie", Context.MODE_PRIVATE)

    fun storeToken(token: String){
        sharedPres.edit { putString("token", token) }
    }

    fun getToken(): String?{
        sharedPres.getString("token", null)?.let {
            return it
        }
        return null
    }
}