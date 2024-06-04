package com.example.greenifyme.ui.admin.home

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.ui.shared.SharedAppBar

@Composable
fun AdminHomeAppBar(
    text: String = "AppBar Text",
    onNotificationsIcon: () -> Unit = {},
    onMaterials: () -> Unit = {},
    onExit: () -> Unit = {},
) {
    SharedAppBar(
        isTextAnimated = true, text = text,
    ) {
        IconButton(onNotificationsIcon) {
            Icon(
                painter = getVector(R.drawable.bell),
                contentDescription = getString(R.string.admin_home_app_bar_notifications),
                modifier = Modifier.size(22.dp)
            )
        }
        IconButton(onMaterials) {
            Icon(
                painter = getVector(R.drawable.file_edit),
                contentDescription = getString(R.string.admin_home_app_bar_edit_list),
                modifier = Modifier.size(22.dp)
            )
        }
        IconButton(onExit) {
            Icon(
                painter = getVector(R.drawable.exit),
                contentDescription = getString(R.string.admin_home_app_bar_exit),
                modifier = Modifier.size(22.dp)
            )
        }
    }
}