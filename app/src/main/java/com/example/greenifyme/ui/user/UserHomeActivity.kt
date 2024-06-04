package com.example.greenifyme.ui.user

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.SharedModelProviderWithAccount
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.account.toAccount
import com.example.greenifyme.data.account.toBundle
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedLazyColumn
import com.example.greenifyme.ui.shared.tip_of_day.TipOfDay
import com.example.greenifyme.ui.user.form.UserFormActivity
import com.example.greenifyme.ui.user.home.CitizenPoints
import com.example.greenifyme.ui.user.home.UserHomeModel

class UserHomeActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val account = intent.getBundleExtra("AccountIdToLoginIn")
        val useSampleData = intent.getBooleanExtra("UseSampleData", false)

        setContent {
            ComposeTheme {
                UserHomeScreen(
                    account = account?.toAccount() ?: Account(
                        accountId = 1,
                        name = "John Deere",
                        points = 100
                    ),
                    useSampleData = useSampleData
                )
            }
        }
    }
}

/**
 * This composable represents the User Home screen.
 *
 * @param account The account data to display.
 * @param useSampleData Whether to use sample data or not.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun UserHomeScreen(
    account: Account = Account(), useSampleData: Boolean = false
) {
    val model: UserHomeModel = viewModel(
        factory = SharedModelProviderWithAccount.Factory(useSampleData, account)
    )
    model.whenAccountReceived(account)

    val state by model.state.collectAsState()
    val tipState by model.tipState.collectAsState()
    val context = LocalContext.current as Activity

    //if it last with "ς" remove it
    val accountFirstName = account.name.split(" ")[0].let {
        if (it.last() == 'ς') it.dropLast(1) else it
    }

    SharedLazyColumn(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    val intent = Intent(context, UserFormActivity::class.java).apply {
                        putExtra("AccountIdToLoginIn", account.toBundle())
                        putExtra("UseSampleData", useSampleData)
                    }
                    context.startActivity(intent)
                },
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    Icons.Outlined.Add, getString(R.string.user_home_fab)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = getString(R.string.user_home_fab))
            }
        }
    ) {
        item {
            AppBar(stringResource(state.greetingText, accountFirstName))
        }
        item { TipOfDay(tipState) }
        item { CitizenPoints(model) }
    }

    if (!state.hasShowedOnce && !account.hasIntroViewed) {
        WelcomeDialog {
            model.setShouldShowOnce()
        }
    }
}

/**
 * This composable represents the welcome dialog.
 *
 * @param onDismiss The callback to invoke when the dialog is dismissed.
 */
@Composable
@Preview
fun WelcomeDialog(onDismiss: () -> Unit = {}) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(getString(R.string.user_home_welcome_dialog_title))
        },
        text = {
            Text(getString(R.string.user_home_welcome_dialog_message))
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(getString(R.string.user_home_welcome_dialog_button_ok))
            }
        }
    )
}


/**
 * This composable represents an app bar with optional animation and an exit button.
 *
 * @param text Text to display in the app bar.
 */
@Composable
@Preview
private fun AppBar(text: String = "Label") {
    val context = LocalContext.current as Activity

    SharedAppBar(text = text, isTextAnimated = true) {
        IconButton(
            onClick = {
                context.finish()
            },
            content = {
                Icon(
                    painterResource(id = R.drawable.exit),
                    contentDescription = "Exit",
                    modifier = Modifier.width(22.dp)
                )
            }
        )
    }
}