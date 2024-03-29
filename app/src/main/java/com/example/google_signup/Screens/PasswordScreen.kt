package com.example.google_signup.Screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.google_signup.R
import com.example.google_signup.navigation.AppScreens
import com.example.google_signup.ui.theme.Google_signupTheme


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PasswordScreen(navController: NavController?, text:String?){
    var userPassword by remember {
        mutableStateOf("")
    }
    var errorNoPassword by remember {
        mutableStateOf(false)
    }

    val vibrationCall = VibrationClass()
    val vibrator = vibrationCall.returnVibrator()
    val vibrationEffect1 = vibrationCall.getVibrationEffect()

    Scaffold(
        bottomBar = {
            Row(
                Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 0.dp,
                        bottom = 10.dp
                    )
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                if (userPassword.isEmpty()) {
                    Button(
                        onClick = {
                            errorNoPassword = true
                            Log.i("BUZZZ", "This is vibrating from the Vibration Class")
                            vibrator.cancel()
                            vibrator.vibrate(vibrationEffect1)
                        },
                        shape = RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomEnd = 5.dp,
                            bottomStart = 5.dp
                        )
                    ) {
                        Text(text = stringResource(id = R.string.next_btn))
                    }

                } else {
                    Button(
                        onClick = {
                            navController?.navigate(route = AppScreens.emailScreen.route)
                        },
                        shape = RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomEnd = 5.dp,
                            bottomStart = 5.dp
                        )
                    ) {
                        Text(stringResource(R.string.next_btn))
                    }
                }
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()

        ) {
            Image(
                painter = painterResource(R.drawable.google_logo),
                contentDescription = stringResource(R.string.logo_desc),
                colorFilter = ColorFilter.tint(Color.Red),
                modifier = Modifier.size(80.dp)
            )

            Text(stringResource(R.string.password_title),fontSize = 30.sp)

            Spacer(modifier = Modifier.size(15.dp))

            text?.let{
                UserCard(userName = text)
            }

            Spacer(modifier = Modifier.size(30.dp))

            PasswordTextField(password = userPassword,errorNoPassword ) {
                userPassword = it
            }
        }
    }
}

//TODO finish this

@RequiresApi(Build.VERSION_CODES.S)
@Preview(
    showBackground = true,
    name = "Light",
    showSystemUi = true,
    backgroundColor = 0xFFFCFCFF
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark",
    showSystemUi = true,
    backgroundColor = 0xFF1A1C1E
)
@Composable
private fun Preview(){
    Google_signupTheme {
        PasswordScreen(null, text = "Manolo@gmail.com" )
    }
}
