package com.example.greenifyme.ui.admin.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.compose_utilities.theme.ComposeTheme

@Composable
fun AdminNotifications() {
    //val model = AdminNotificationsModel()
    //val state by model.adminHomeState.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
        modifier = Modifier.fillMaxSize() //We want the surface to fill the entire screen
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 2.dp)
        ) {

        }

    }

}


@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        AdminNotifications()
    }
}
