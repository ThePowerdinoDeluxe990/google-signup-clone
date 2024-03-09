package com.example.google_signup.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.*
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import com.example.google_signup.R

@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("ServiceCast")
@Composable
fun emailScreenLogin(value:String,errorText:Boolean, onValueChange: (String) -> Unit){

    //For redirect
    val uriHandler = LocalUriHandler.current

    //The login Ui
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ),
    ){
        Image(
            painter = painterResource(R.drawable.google_logo),
            contentDescription = stringResource(R.string.logo_desc),
            colorFilter = ColorFilter.tint(Color.Red),
            modifier = Modifier.size(80.dp)
        )
        Text(stringResource(R.string.sign_in), fontSize = 30.sp)
        Spacer(modifier = Modifier.size(15.dp))

        //Google Account and Learn more
        Row {
            Text(stringResource(id = R.string.google_account))
            Spacer(Modifier.size(5.dp))
            // TODO Put this in another file
            link_Text(R.string.learn_more,uriHandler)
        }

        Spacer(modifier = Modifier.size(30.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(stringResource(R.string.sing_in_with)) },
            singleLine = true,
            isError = errorText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 25.dp,
                    end = 25.dp
                )
        )

        Spacer(Modifier.size(10.dp))
        if(errorText){
            ErrorComposable()

        }
    }

    //Forgot email hack
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 25.dp,
                top = 10.dp
            )
    ) {
        link_Text(linkString = R.string.create_account, uriHandler = uriHandler)
    }
}

