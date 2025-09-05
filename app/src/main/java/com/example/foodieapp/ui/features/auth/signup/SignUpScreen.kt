package com.example.foodieapp.ui.features.auth.signup

import android.widget.Toast
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.foodieapp.R
import com.example.foodieapp.ui.AuthTextFields
import com.example.foodieapp.ui.GroupSocialButtons
import com.example.foodieapp.ui.theme.Orange
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val loading = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = uiState.value) {
        when(uiState.value){
            is SignUpViewModel.SignUpEvent.Success -> {
                loading.value = false
                errorMessage.value = null
            }

            SignUpViewModel.SignUpEvent.Error -> {
                loading.value = false
                errorMessage.value = "Failed"
            }
            SignUpViewModel.SignUpEvent.Loading -> {
                loading.value = true
                errorMessage.value = null
            }
            else -> {

            }
        }
    }
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.navigationEvent.collectLatest { result ->
            when(result){
                is SignUpViewModel.SignUpNavigationEvent.NavigationToHome -> {
                    Toast.makeText(
                        context,
                        "Success",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
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
                    text = "Sign Up",
                    fontSize = 32.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                )
                AuthTextFields(viewModel.fullName.value ,{viewModel.onEvent(SignUpScreenEvent.OnFullNameChange(it))}, "Full Name"){

                }
                AuthTextFields(viewModel.email.value ,{viewModel.onEvent(SignUpScreenEvent.OnEmailChange(it))}, "E-mail"){

                }
                AuthTextFields(viewModel.password.value ,{viewModel.onEvent(SignUpScreenEvent.OnPasswordChange(it))}, "Password", visualTransformation = PasswordVisualTransformation()){
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {viewModel.onSignUpClick()}, colors = ButtonDefaults.buttonColors(containerColor = Orange), modifier = Modifier
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
                                        text = stringResource(id = R.string.sign_up_btn),
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 32.dp)
                                    )
                                }
                            }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.already),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { },
                    fontWeight = FontWeight.SemiBold
                )
                GroupSocialButtons(onFacebookClicked = {}, color = Color.Black) { }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SignUpScreen()
}