package com.example.foodieapp.data

import com.example.foodieapp.data.model.AuthResponse
import com.example.foodieapp.data.model.OAuthRequest
import com.example.foodieapp.data.model.SignInRequest
import com.example.foodieapp.data.model.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodAPI {
    @GET("/food")
    suspend fun getAllFood() : List<String>

    @POST("/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): AuthResponse

    @POST("/auth/login")
    suspend fun signIn(@Body request: SignInRequest): AuthResponse

    @POST("/auth/oauth")
    suspend fun oAuth(@Body request: OAuthRequest): AuthResponse
}