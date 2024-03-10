package com.example.google_signup.Screens

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class Vibration_Class {

    @Composable
    protected fun returnContext(): Context {
        val context = LocalContext.current
        return context
    }
    @Composable
    fun return_Vibrator(): Vibrator {
        val context = returnContext()
        //Vibration using the latest API and checks if the API is supported
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        return vibrator


    }

    fun Get_Vibration_Effect(): VibrationEffect {
        //The vibration Effect
        val vibrationEffect1: VibrationEffect =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.i("BUZZ","Vibration")
                VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)

            } else {
                Log.e("TAG", "Cannot vibrate device..")
                TODO("VERSION.SDK_INT < O")
            }
        return vibrationEffect1
    }

}