package com.example.greenifyme.ui.admin.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun AdminHomeAppBar(
    model: AdminHomeModel = AdminHomeModel()
) {
    val homeSearchUiState by model.adminHomeState.collectAsState()
    val googleSansFontFamily = FontFamily(
        Font(R.font.google_sans_regular),
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        val textmodifier = Modifier
            .padding(start = 16.dp, top = 12.dp)

        Text(
            text = homeSearchUiState.greetingText,
            modifier = textmodifier,
            style = TextStyle(
                fontFamily = googleSansFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
            ),
        )
        IconButton(
            onClick = { },
            content = {
                Icon(
                    painterResource(
                        id = R.drawable.baseline_notifications_24,
                    ),
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