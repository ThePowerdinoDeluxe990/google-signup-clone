package com.example.google_signup.Screens

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun link_Text(@StringRes linkString:Int, uriHandler: UriHandler,modifier: Modifier) {
    Text(
        text = stringResource(linkString),
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .clickable {
                uriHandler.openUri("https://www.google.com/")
            }
    )
}