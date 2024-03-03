package com.example.google_signup.Screens

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import androidx.compose.ui.platform.LocalUriHandler
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun emailScreen(navController: NavController){
    Scaffold {
        emailScreenContent(navController)
    }
}

// The Error composable
@Composable
private fun ErrorComposable(){
    Row {
        Icon(
            Icons.Outlined.Info,
            contentDescription = stringResource(R.string.error_icon_description),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(Modifier.size(10.dp))
        Text(
            text = stringResource(R.string.error_message),
            color = MaterialTheme.colorScheme.error,
        )
    }
}

@Composable
private fun emailScreenLogin(value:String,errorText:Boolean, onValueChange: (String) -> Unit){

    //For redirect
    val uriHandler = LocalUriHandler.current

    //The login Ui
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
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
            Text(
                text= stringResource(R.string.learn_more),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        uriHandler.openUri("https://www.google.com/")
                    }
            )
        }

        Spacer(modifier = Modifier.size(30.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {Text(stringResource(R.string.sing_in_with))},
            singleLine = true,
            isError = errorText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start =25.dp,
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
        modifier = Modifier.fillMaxWidth()
            .padding(
                start = 25.dp,
                top = 10.dp
            )
    ) {
        Text(
            text = stringResource(R.string.forgot),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    uriHandler.openUri("https://www.google.com/")
                }
        )
    }
}

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
                Text(
                    stringResource(R.string.create_account),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(
                            bottom = 10.dp
                        )
                        .clickable {
                            uriHandler.openUri("https://www.google.com/")
                        }
                )

                //Checking if the textfield is empty
                if (userEmail.isEmpty()) {
                    Button(
                        onClick = {
                            errorTextField = true
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

        Column(
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            emailScreenLogin(
                userEmail,
                onValueChange = { userEmail = it },
                errorText = errorTextField
            )
        }
    }
}

//Dark and light mode preview code
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

