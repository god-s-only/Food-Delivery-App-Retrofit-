package com.example.foodieapp.ui.features.auth.signin

import com.example.foodieapp.ui.features.auth.signup.SignUpScreenEvent

sealed class SignInScreenEvent {
    data class OnEmailChange(val email: String): SignInScreenEvent()
    data class OnPasswordChange(val password: String): SignInScreenEvent()
}