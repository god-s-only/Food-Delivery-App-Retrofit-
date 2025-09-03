package com.example.foodieapp.ui.features.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodieapp.R

@Composable
fun SignUpScreen() {

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

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SignUpScreen()
}