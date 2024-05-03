package com.example.greenifyme.ui.admin.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.AdminHomeViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun AdminHomeAppBar(
    model: AdminHomeModel = AdminHomeModel()
) {
    val homeSearchUiState by model.adminHomeState.collectAsState()


    Row(
        modifier = Modifier.fillMaxWidth()
        //It is not necessary to add a fixed height, the
        // height will be calculated according to the content
    ) {
        Text(text = homeSearchUiState.greetingText)
        Button(
            onClick = {
                model.changeGreetingText()
            },
            content = { Text("Click") }
        )
        IconButton(
            onClick = { },
            content = {
                Icon(
                    painterResource(
                        //Replace it with an outline icon if you want
                        id = R.drawable.baseline_notifications_24,
                    ),
                    //This contentDescription is for accessibility
                    contentDescription = "Notifications"
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