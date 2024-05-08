package com.example.greenifyme.ui.admin.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme

@Composable
fun AdminHomeAppBar(
    model: AdminHomeModel = AdminHomeModel(),
    state: AdminHomeState = AdminHomeState(model.getGreetingTextFromTime()),
    horizontalPadding: Dp = 12.dp
) {
    val googleSansFontFamily = FontFamily(
        Font(R.font.google_sans_regular),
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            text = state.greetingText,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.W600,
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { },
            content = {
                Icon(
                    //The most common icons are available in the Icons class
                    Icons.Outlined.Notifications,
                    //This contentDescription is for accessibility
                    contentDescription = "Notifications"
                )
            }
        )
        IconButton(
            onClick = { },
            content = {
                Icon(
                    painterResource(id = R.drawable.baseline_exit_to_app_24),
                    contentDescription = "Exit"
                )
            }
        )
    }
}


@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        AdminHomeAppBar()
    }
}