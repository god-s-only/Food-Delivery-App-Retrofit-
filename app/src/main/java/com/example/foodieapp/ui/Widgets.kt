package com.example.foodieapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodieapp.R
import com.example.foodieapp.ui.theme.Orange

@Composable
fun GroupSocialButtons(
    color:Color = Color.White,
    onFacebookClicked:() -> Unit,
    onGoogleClicked:() -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            HorizontalDivider(modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp), color = color, thickness = 1.dp)
            Text(
                text = stringResource(id = R.string.alt),
                color = color,
                modifier = Modifier.padding(8.dp)
            )
            HorizontalDivider(modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp), color = color, thickness = 1.dp)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SocialButton(icon = R.drawable.ic_facebook, title = R.string.sign_in_with_facebook) {onFacebookClicked }
            SocialButton(icon = R.drawable.ic_google, title = R.string.sign_in_with_google) { onGoogleClicked}
        }
    }
}

@Composable
fun SocialButton(
    icon:Int,
    title:Int,
    onClick:() -> Unit
) {
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(38.dp)
        ){
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = title),
                color = Color.Black
            )
        }
    }
}

@Composable
fun AuthTextFields(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = label,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
    OutlinedTextField(
        value = value,
        onValueChange = {onValueChange(it)},
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors().copy(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedIndicatorColor = Orange.copy(alpha = 0.3f),
            unfocusedIndicatorColor = Orange.copy(alpha = 0.4f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon
    )

}

@Preview
@Composable
private fun DefaultPreview() {
    GroupSocialButtons(onFacebookClicked = {}){}
}