package com.example.foodieapp.ui.features.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodieapp.R
import com.example.foodieapp.ui.AuthTextFields
import com.example.foodieapp.ui.GroupSocialButtons
import com.example.foodieapp.ui.theme.Orange

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.weight(1f))
                AuthTextFields("" ,{}, "Password", visualTransformation = PasswordVisualTransformation()){

                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Orange), modifier = Modifier.height(48.dp)) {
                    Text(
                        text = stringResource(id = R.string.sign_up_btn),
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.already),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {  },
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