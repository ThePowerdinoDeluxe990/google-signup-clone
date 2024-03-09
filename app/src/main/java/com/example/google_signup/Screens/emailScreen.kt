package com.example.google_signup.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.google_signup.R
import com.example.google_signup.navigation.AppScreens
import com.example.google_signup.ui.theme.Google_signupTheme

//For the app navigation
@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun emailScreen(navController: NavController){
    Scaffold {
        emailScreenContent(navController)
    }
}





@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun emailScreenContent(navController: NavController?){
    //States

    var userEmail by remember {
        mutableStateOf("")
    }
    var errorTextField by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    //For redirect
    val uriHandler = LocalUriHandler.current

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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
                ) {

                link_Text(linkString = R.string.create_account, uriHandler = uriHandler )

                //Checking if the textfield is empty
                if (userEmail.isEmpty()) {
                    Button(
                        onClick = {
                            errorTextField = true
                            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                            val vibrationEffect1: VibrationEffect =
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    Log.i("BUZZ","Vibration")
                                    VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)

                                } else {
                                    Log.e("TAG", "Cannot vibrate device..")
                                    TODO("VERSION.SDK_INT < O")
                                }
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
                        Text(text = "Next")
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
            emailScreenLogin(
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
private fun emailScreenPreview() {
    Google_signupTheme {
        Surface{
           emailScreenContent(null)
        }
    }
}

