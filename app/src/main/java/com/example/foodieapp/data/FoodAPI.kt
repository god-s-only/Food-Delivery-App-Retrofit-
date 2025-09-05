package com.example.foodieapp.data

import com.example.foodieapp.data.model.SignUpRequest
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodAPI {
    @GET("/food")
    suspend fun getAllFood() : List<String>

    @POST("/auth/signup")
    suspend fun signUp(request: SignUpRequest): AuthResponse
}