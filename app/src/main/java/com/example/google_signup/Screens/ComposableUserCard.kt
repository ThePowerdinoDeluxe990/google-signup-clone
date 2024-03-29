package com.example.google_signup.Screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.google_signup.R
import com.example.google_signup.ui.theme.Google_signupTheme


@Composable
fun UserCard(userName:String){
    var showAlertDialog by remember {
        mutableStateOf(false)
    }

    if(showAlertDialog){
        AlertDialogDefault(
            onDismissRequest = { showAlertDialog = false },
            onConfirmation = { showAlertDialog = false},
            dialogTitle = "User Info",
            dialogSubtitle = userName
        )
    }

    OutlinedCard(
        border = BorderStroke(1.dp,MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(
           containerColor = MaterialTheme.colorScheme.background
       ),

       ){
           Row(
               Modifier.padding(
                   2.dp
               )
           ){

               Spacer(Modifier.size(2.dp))

               Icon(
                   Icons.Rounded.AccountCircle,
                   tint = MaterialTheme.colorScheme.onBackground,
                   contentDescription = stringResource(R.string.user_icon_desc)
               )

               Spacer(Modifier.size(7.dp))

               Text(
                   text =userName,
                   color = MaterialTheme.colorScheme.onBackground
               )

               Spacer(Modifier.size(2.dp))

               Icon(
                   Icons.Outlined.KeyboardArrowDown,
                   contentDescription = stringResource(R.string.dropdown_icon_desc),
                   modifier = Modifier.clickable {
                       showAlertDialog = true
                   }
               )

               Spacer(Modifier.size(3.dp))
       }
    }
}


@Composable
fun AlertDialogDefault(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogSubtitle:String

) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(
                text= dialogSubtitle
            )
               },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Ok")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Exit")
            }
        }
    )
}
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light",
    backgroundColor = 0xFFFCFCFF
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark",
    backgroundColor = 0xFF1A1C1E
)
@Composable
private fun Preview(){
    Google_signupTheme {
        UserCard(userName = "Pepito@gmail.com")
    }
}