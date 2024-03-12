package com.example.google_signup.navigation

sealed class AppScreens(val route:String) {
    object emailScreen: AppScreens("EmailScreen")
    object passwordScreen: AppScreens("PasswordScreen")
}