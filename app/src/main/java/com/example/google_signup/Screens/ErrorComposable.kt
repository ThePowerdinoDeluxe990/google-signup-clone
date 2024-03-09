package com.example.google_signup.Screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.google_signup.R

// The Error composable
@Composable
fun ErrorComposable(){
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