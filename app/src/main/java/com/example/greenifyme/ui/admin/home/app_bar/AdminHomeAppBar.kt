package com.example.greenifyme.ui.admin.home.app_bar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.AdminHomeModel
import com.example.greenifyme.ui.admin.home.AdminHomeState

@Composable
fun AdminHomeAppBar(
    model: AdminHomeModel = AdminHomeModel(),
    state: AdminHomeState = AdminHomeState(),
    horizontalPadding: Dp = 12.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        AnimatedGreeting(state.greetingText)
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