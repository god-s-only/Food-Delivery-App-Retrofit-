package com.example.foodieapp.ui.features.auth.signin

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.foodieapp.R
import com.example.foodieapp.ui.AuthTextFields
import com.example.foodieapp.ui.GroupSocialButtons
import com.example.foodieapp.ui.features.navigation.AuthScreen
import com.example.foodieapp.ui.features.navigation.Home
import com.example.foodieapp.ui.features.navigation.Login
import com.example.foodieapp.ui.features.navigation.SignUp
import com.example.foodieapp.ui.theme.Orange
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val loading = remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.value) {
        when(uiState.value){
            is SignInViewModel.SignInEvent.Success -> {
                loading.value = false
                errorMessage.value = null
            }

            SignInViewModel.SignInEvent.Error -> {
                loading.value = false
                errorMessage.value = "Failed"
            }
            SignInViewModel.SignInEvent.Loading -> {
                loading.value = true
                errorMessage.value = null
            }
            else -> {

            }
        }
    }
    LaunchedEffect(true) {
        viewModel.navigationEvent.collectLatest { result ->
            when(result){
                is SignInViewModel.SignInNavigationEvent.NavigationToHome -> {
                    navController.navigate(Home)
                }
                is SignInViewModel.SignInNavigationEvent.NavigationToSignUp -> {
                    navController.navigate(SignUp)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)){
            Image(
                painter = painterResource(id = R.drawable.ic_auth_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Box(modifier = Modifier.weight(1f))
                Text(
                    text = "Sign In",
                    fontSize = 32.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                )

                AuthTextFields(viewModel.email.value ,{viewModel.onEvent(SignInScreenEvent.OnEmailChange(it))}, "E-mail"){

                }
                AuthTextFields(viewModel.password.value ,{viewModel.onEvent(SignInScreenEvent.OnPasswordChange(it))}, "Password", visualTransformation = PasswordVisualTransformation()){
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {viewModel.onSignInClick()}, colors = ButtonDefaults.buttonColors(containerColor = Orange), modifier = Modifier
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally)) {
                    Box{
                        AnimatedContent(targetState = loading.value, transitionSpec = {
                            fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.8f) togetherWith
                                    fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f)
                        }) {target ->
                            if(target){
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(horizontal = 32.dp)
                                        .size(24.dp)
                                )
                            }else{
                                Text(
                                    text = "Sign In",
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 32.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.sign_up_redirect),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { viewModel.onSignUpClicked() },
                    fontWeight = FontWeight.SemiBold
                )
                GroupSocialButtons(onFacebookClicked = {}, color = Color.Black) {
                }
            }
        }
    }
}