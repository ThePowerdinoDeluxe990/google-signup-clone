package com.example.google_signup.Screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.google_signup.R
import com.example.google_signup.navigation.AppScreens
import com.example.google_signup.ui.theme.Google_signupTheme

//For the app navigation
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EmailScreen(navController: NavController){
    Scaffold {
        EmailScreenContent(navController)
    }
}


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NewApi")
private fun EmailScreenContent(navController: NavController?){
    //States

    var userEmail by remember {
        mutableStateOf("")
    }
    var errorTextField by remember {
        mutableStateOf(false)
    }

    //Vibration variables
    val vibrationCall = VibrationClass()
    val vibrator = vibrationCall.returnVibrator()
    val vibrationEffect1 = vibrationCall.getVibrationEffect()

    Scaffold(
        //Next and create account buttons
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
                //Checking if the textfield is empty

                if (userEmail.isEmpty()) {
                    Button(
                        onClick = {
                            errorTextField = true
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
                            navController?.navigate(
                                route = AppScreens.passwordScreen.route + "/$userEmail"
                            )
                        },
                        shape = RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomEnd = 5.dp,
                            bottomStart = 5.dp
                        )
                    ) {
                        Text(text = stringResource(R.string.next_btn))
                    }
                }
            }
        }
    ){
        Column{
            Spacer(modifier = Modifier.size(15.dp))
                EmailScreenLogin(
                    userEmail,
                    onValueChange = { userEmail = it },
                    errorText = errorTextField,
                )
        }
    }
}

//Dark and light mode preview code
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
private fun EmailScreenPreview() {
    Google_signupTheme {
        Surface{
           EmailScreenContent(null)
        }
    }
}

