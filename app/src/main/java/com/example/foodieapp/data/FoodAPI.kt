package com.example.foodieapp.data

import com.example.foodieapp.data.model.AuthResponse
import com.example.foodieapp.data.model.CategoriesResponse
import com.example.foodieapp.data.model.OAuthRequest
import com.example.foodieapp.data.model.SignInRequest
import com.example.foodieapp.data.model.SignUpRequest
import retrofit2.http.Body
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodAPI {
    @GET("/categories")
    suspend fun getCategories() : Response<CategoriesResponse>

    @POST("/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<AuthResponse>

    @POST("/auth/login")
    suspend fun signIn(@Body request: SignInRequest): Response<AuthResponse>

    @POST("/auth/oauth")
    suspend fun oAuth(@Body request: OAuthRequest): Response<AuthResponse>
}