package com.example.greenifyme.ui.database.manager.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

data class ManagerNavigationItem(
    val destination: DatabaseManagerNavigationDestination,
    val titleRes: Int,
    val label: @Composable () -> Unit = {
        Text(stringResource(id = titleRes))
    },
    val filledVector: Int,
    val filledIcon: @Composable () -> Unit = {
        Icon(
            painter = painterResource(id = filledVector),
            contentDescription = stringResource(id = titleRes),
            modifier = Modifier
                .size(26.dp)
                .padding(vertical = 3.dp)
        )
    },
    val outlineVector: Int,
    val outlineIcon: @Composable () -> Unit =
        {
            Icon(
                painter = painterResource(id = outlineVector),
                contentDescription = stringResource(id = titleRes),
                modifier = Modifier
                    .size(26.dp)
                    .padding(vertical = 3.dp)
            )
        }
)