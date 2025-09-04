package com.example.foodieapp.ui.features.auth.signup

sealed class SignUpScreenEvent{
    data class OnFullNameChange(val fullName: String): SignUpScreenEvent()
    data class OnEmailChange(val email: String): SignUpScreenEvent()
    data class OnPasswordChange(val password: String): SignUpScreenEvent()
}