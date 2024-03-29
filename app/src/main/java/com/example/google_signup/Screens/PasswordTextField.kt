package com.example.google_signup.Screens

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.google_signup.R
import com.example.google_signup.ui.theme.Google_signupTheme

@Composable
fun PasswordTextField(password:String,errorOrNot:Boolean, onValueChange:(String)-> Unit){

    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        Modifier.animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )
        ) {
       OutlinedTextField(
           modifier = Modifier
               .fillMaxWidth()
               .padding(
                   start = 25.dp,
                   end = 25.dp
               ),
           value = password,
           onValueChange = onValueChange,
           isError =  errorOrNot,
           placeholder = { Text(text = stringResource(R.string.put_password_desc))},
           singleLine = true,
           visualTransformation = if (passwordVisible) {

               VisualTransformation.None

           } else {

               PasswordVisualTransformation()

           },
           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
       )

        Spacer(modifier = Modifier.size(15.dp))

        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            if(errorOrNot){

                ErrorComposable()
            }else if(errorOrNot == false){

            }
        }
        ShowAndHide(
            Modifier.padding(
                 25.dp
            ),
            ShoworHide = passwordVisible,
            ) {
           passwordVisible = !passwordVisible
        }

   }
}


@Composable
private fun ShowAndHide(modifier: Modifier,ShoworHide:Boolean, onCheckedChange:(Boolean) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(
            start = 15.dp
        )
    ){
        Checkbox(
            checked = ShoworHide,
            onCheckedChange = onCheckedChange
        )

        Text(
            stringResource(R.string.show_password)
        )
    }
}

@Preview(
    showBackground = true,
    name = "Light",
    backgroundColor = 0xFFFCFCFF
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark",
    backgroundColor = 0xFF1A1C1E
)
@Composable
private fun Preview(){
    Google_signupTheme {
        PasswordTextField(password = "", errorOrNot = false) {
            
        }
    }
}