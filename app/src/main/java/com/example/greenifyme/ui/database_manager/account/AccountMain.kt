package com.example.greenifyme.ui.database_manager.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.database_manager.LargeTextAtCenter
import com.example.greenifyme.ui.database_manager.account.accountModel.AccountModel
import com.example.greenifyme.ui.database_manager.account.bottom_sheet.AccountBottomSheet
import com.example.greenifyme.ui.database_manager.account.home.AccountList
import com.example.greenifyme.ui.database_manager.account.home.AccountSearchArea

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountMain(
    model: AccountModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val accountItems by model.accountItems.collectAsState()
    val accountUiState by model.accountUiState.collectAsState()

    Scaffold(
        containerColor = Color.Transparent,
        floatingActionButton = {
            FloatingActionButton(
                content = {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.newAccountItem)
                    )
                },
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = { model.openSheetAndSwitchToCreateMode() },
            )
        },
    ) {
        if (accountUiState.showBottomSheet) {
            AccountBottomSheet(model = model, accountUiState = accountUiState)
        }
        Column {
            AccountSearchArea(model = model, uiState = accountUiState)
            Spacer(modifier = Modifier.height(14.dp))
            if (accountItems.isEmpty()) LargeTextAtCenter(R.string.empty_database)
            else AccountList(model = model, accountItems = accountItems)
        }
    }
}



