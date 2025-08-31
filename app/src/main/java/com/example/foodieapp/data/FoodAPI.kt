package com.example.foodieapp.data

import retrofit2.http.GET

interface FoodAPI {
    @GET("/food")
    suspend fun getAllFood() : List<String>
}