package com.example.google_signup.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.google_signup.Screens.EmailScreen
import com.example.google_signup.Screens.PasswordScreen

//The navigation of the app
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.emailScreen.route,

        //Kinemaster transitions
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(700)
            )+ fadeOut(animationSpec = tween(1000))
        },
        enterTransition = {
              fadeIn(animationSpec = tween(1000))
        },
        ){
        composable(route = AppScreens.emailScreen.route){
            EmailScreen(navController)
        }
        composable(route = AppScreens.passwordScreen.route + "/{text}", arguments = listOf(
            navArgument(name ="text"){
          type= NavType.StringType
        })
        ){
            PasswordScreen(navController, it.arguments?.getString("text"))
        }
    }
}


