package com.example.google_signup.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.google_signup.R
import com.example.google_signup.navigation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun passwordScreen(navController: NavController, text:String?){
    Scaffold {
        passwordScreenContent(navController,text)
    }
}

// @TODO finish this
@Composable
private fun passwordScreenContent(navController: NavController, text: String?){
    Column {
        Text(stringResource(R.string.password_title))
        text?.let{
            Text(it)
        }
        Button(onClick = {
            navController.navigate(route= AppScreens.emailScreen.route)
        }) {
            Text(stringResource(R.string.next_btn))
        }
    }
}

