package com.example.foodieapp.ui.features.auth.signin

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodieapp.data.FoodAPI
import com.example.foodieapp.data.auth.GoogleAuthUIProvider
import com.example.foodieapp.data.model.OAuthRequest
import com.example.foodieapp.data.model.SignInRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val foodAPI: FoodAPI): ViewModel() {

    private val googleAuthUIProvider = GoogleAuthUIProvider()
    private val _uiState = MutableStateFlow<SignInEvent>(SignInEvent.Nothing)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<SignInNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    var email = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    fun onEvent(event: SignInScreenEvent){
        when(event){
            is SignInScreenEvent.OnEmailChange -> {
                email.value = event.email
            }

            is SignInScreenEvent.OnPasswordChange -> {
                password.value = event.password
            }
        }
    }

    fun onSignInClick(){
        viewModelScope.launch {
            try {
                _uiState.value = SignInEvent.Loading
                val response = foodAPI.signIn(
                    SignInRequest(
                        email = email.value,
                        password = password.value
                    )
                )
                if(response.token.isNotEmpty()){
                    _uiState.value = SignInEvent.Success
                    _navigationEvent.emit(SignInNavigationEvent.NavigationToHome)
                }
            }catch (e: Exception){
                e.printStackTrace()
                _uiState.value = SignInEvent.Error
            }
        }
    }

    fun onGoogleSignInClicked(context: Context){
        viewModelScope.launch {
            _uiState.value = SignInEvent.Loading
            val response = googleAuthUIProvider.signIn(
                context,
                CredentialManager.create(context)
            )
            if(response != null){
                val request = OAuthRequest(
                    token = response.token,
                    provider = "google"
                )
                val res = foodAPI.oAuth(request)
                if(res.token.isNotEmpty()){
                    _uiState.value = SignInEvent.Success
                    _navigationEvent.emit(SignInNavigationEvent.NavigationToHome)
                }else{
                    _uiState.value = SignInEvent.Error
                }

            }else{
                _uiState.value = SignInEvent.Error
            }
        }
    }

    fun onSignUpClicked(){
        viewModelScope.launch {
            _navigationEvent.emit(SignInNavigationEvent.NavigationToSignUp)
        }

    }

    sealed class SignInNavigationEvent{
        data object NavigationToSignUp: SignInNavigationEvent()
        data object NavigationToHome: SignInNavigationEvent()
    }

    sealed class SignInEvent{
        data object Nothing: SignInEvent()
        data object Loading: SignInEvent()
        data object Success: SignInEvent()
        data object Error: SignInEvent()
    }
}