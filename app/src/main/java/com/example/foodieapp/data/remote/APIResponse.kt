package com.example.foodieapp.data.remote

import retrofit2.Response

sealed class APIResponse<out T> {
    data class Success<out T>(val data: T): APIResponse<T>()
    data class Error<out T>(val code: Int, val message: String): APIResponse<T>(){
        fun formatMessage(): String{
            return "Error $code $message"
        }
    }
    data class Exception(val exception: kotlin.Exception): APIResponse<Nothing>()
}

suspend fun<T> safeApiCall(apiCall: suspend () -> Response<T>): APIResponse<T>{
    return try {
        val res = apiCall.invoke()
        if(res.isSuccessful){
            APIResponse.Success(res.body()!!)
        }else{
            APIResponse.Error(code = res.code(), message = res.errorBody()?.string() ?: "Unknown Error")
        }
    }catch (e: Exception){
        APIResponse.Exception(e)
    }
}