package com.example.greenifyme.ui.user.form

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.ViewModelProviderWithParam
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.account.toAccount
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedColumn
import com.example.greenifyme.ui.user.form.dialog.UserFormDialogMain
import com.example.greenifyme.ui.user.form.list.UserFormBottomButtons
import com.example.greenifyme.ui.user.form.list.UserFormList
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class UserFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val account = intent.getBundleExtra("AccountIdToLoginIn")

        setContent {
            ComposeTheme {
                UserFormMain(
                    account?.toAccount() ?: Account(name = "John Deere", points = 100)
                )
            }
        }
    }
}

/**
 * This is the main composable for the User Form screen. It is responsible for rendering the entire screen.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun UserFormMain(account: Account = Account()) {
    val model: UserFormModel = viewModel(
        factory = ViewModelProviderWithParam.Factory(account)
    )

    val state by model.state.collectAsState()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && state.askPermission) {
        val cameraPermissionState = rememberPermissionState(
            android.Manifest.permission.POST_NOTIFICATIONS
        )
        if (!cameraPermissionState.status.isGranted) {
            LaunchedEffect(true) {
                cameraPermissionState.launchPermissionRequest()
            }
        } else model.submitForm(LocalContext.current as Activity)
    }

    if (state.showDialog) UserFormDialogMain(model, state)
    SharedColumn {
        AppBar(model, state)
        UserFormList(model, state, Modifier.weight(1f))
        UserFormBottomButtons(model, state)
    }
}

@Composable
private fun AppBar(model: UserFormModel, state: UserFormState) {
    val activity = LocalContext.current as Activity
    SharedAppBar(
        text = getString(state.strings.appBarLabel),
    ) {
        TextButton(onClick = { model.quitForm(activity) }) {
            Text(text = getString(state.strings.appBarCancel))
        }
    }
}