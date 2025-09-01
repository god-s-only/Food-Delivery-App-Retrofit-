package com.example.foodieapp.ui.features.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodieapp.R
import com.example.foodieapp.ui.theme.Orange

@Composable
fun AuthScreen() {
    val imageSize = remember { mutableStateOf(IntSize.Zero) }
    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Black
        ),
        startY = imageSize.value.height.toFloat() / 2
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ){
        Image(painter = painterResource(id = R.drawable.welcome_background), contentDescription = null,
            modifier = Modifier
            .onGloballyPositioned {
                imageSize.value  = it.size
            }
            .alpha(0.6f)
            .fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(brush)
        )
        Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color.White), modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(8.dp)
        ) {
            Text(text = stringResource(id = R.string.skip_btn), color = Orange)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 110.dp)
            .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                color = Color.Black,
                modifier = Modifier,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.app_name),
                color = Orange,
                modifier = Modifier,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.desc),
                color = Color.DarkGray,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(16.dp)) {

            TextButton(onClick = {}, modifier = Modifier
                .padding(horizontal = 50.dp)) {
                Text(
                    text = stringResource(id = R.string.already),
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    AuthScreen()
}