package com.example.foodieapp.ui.features.auth.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.data.FoodAPI
import com.example.foodieapp.data.model.OAuthRequest
import com.example.foodieapp.data.model.SignUpRequest
import com.example.foodieapp.ui.features.auth.signin.SignInViewModel.SignInEvent
import com.example.foodieapp.ui.features.auth.signin.SignInViewModel.SignInNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
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

    fun onLoginClicked(){
        viewModelScope.launch {
            _navigationEvent.emit(SignUpNavigationEvent.NavigationToLogin)
        }
    }

    fun onSignUpClick(){
        viewModelScope.launch {
            try {
                _uiState.value = SignUpEvent.Loading
                val response = foodAPI.signUp(
                    SignUpRequest(
                        name = fullName.value,
                        email = email.value,
                        password = password.value
                    )
                )
                if(response.token.isNotEmpty()){
                    _uiState.value = SignUpEvent.Success
                    _navigationEvent.emit(SignUpNavigationEvent.NavigationToHome)
                }
            }catch (e: Exception){
                _uiState.value = SignUpEvent.Error
                e.printStackTrace()
            }catch (io: IOException){
                _uiState.value = SignUpEvent.Error
            }

        }
    }

    fun getToken(token: String){
        viewModelScope.launch {
            _uiState.value = SignUpEvent.Loading
            try {
                val response = foodAPI.oAuth(
                    OAuthRequest(
                        token,
                        "google"
                    )
                )
                if (response.token.isNotEmpty()) {
                    _uiState.value = SignUpEvent.Success
                    _navigationEvent.emit(SignUpNavigationEvent.NavigationToHome)
                } else {
                    _uiState.value = SignUpEvent.Error
                }
            }catch (e: Exception){
                e.printStackTrace()
                _uiState.value = SignUpEvent.Error
            }
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