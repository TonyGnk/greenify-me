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
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedLazyColumn
import com.example.greenifyme.ui.shared.tip_of_day.TipOfDay
import com.example.greenifyme.ui.user.form.UserFormActivity
import com.example.greenifyme.ui.user.home.UserHomeModel
import com.example.greenifyme.ui.user.home.citizen_points.CitizenPoints

class UserHomeActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                UserHome()
            }
        }
    }
}

/**
 * This composable represents the user's home screen layout, displaying various components like app bar, tip of the day, and citizen points.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
private fun UserHome() {
    val model: UserHomeModel = viewModel(factory = ViewModelProvider.Factory)
    val state by model.state.collectAsState()
    val tipState by model.tipState.collectAsState()
    val context = LocalContext.current as Activity

    SharedLazyColumn(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    val intent = Intent(context, UserFormActivity::class.java)
                    context.startActivity(intent)
                },
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    Icons.Outlined.Add, stringResource(R.string.user_home_fab)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = stringResource(R.string.user_home_fab))
            }
        }
    ) {
        item {
            AppBar(getString(state.greetingText))
        }
        item {
            TipOfDay(tipState)
        }
        item {
            CitizenPoints()
        }

    }
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

    SharedAppBar(
        text = text,
        isTextAnimated = true
    ) {
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