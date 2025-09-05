package com.example.foodieapp.ui.features.auth.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.data.FoodAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(private val foodAPI: FoodAPI): ViewModel() {
    private val _uiState = MutableStateFlow<SignUpEvent>(SignUpEvent.Nothing)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<SignUpNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    var fullName = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    fun onEvent(event: SignUpScreenEvent){
        when(event){
            is SignUpScreenEvent.OnEmailChange -> {
                email.value = event.email
            }
            is SignUpScreenEvent.OnFullNameChange -> {
                fullName.value = event.fullName
            }
            is SignUpScreenEvent.OnPasswordChange -> {
                password.value = event.password
            }
        }
    }

    fun onSignUpClick(){
        viewModelScope.launch {

        }
    }



    sealed class SignUpNavigationEvent{
        data object NavigationToLogin: SignUpNavigationEvent()
        data object NavigationToHome: SignUpNavigationEvent()
    }

    sealed class SignUpEvent{
        data object Nothing: SignUpEvent()
        data object Loading: SignUpEvent()
        data object Success: SignUpEvent()
        data object Error: SignUpEvent()
    }

}