package com.example.greenifyme.ui.user.form

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedColumn
import com.example.greenifyme.ui.user.form.dialog.UserFormDialogMain
import com.example.greenifyme.ui.user.form.list.UserFormBottomButtons
import com.example.greenifyme.ui.user.form.list.UserFormList

class UserFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_compose)
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ComposeTheme {
                    UserFormMain()
                }
            }
        }
    }
}

/**
 * This is the main composable for the User Form screen. It is responsible for rendering the entire screen.
 */
@Composable
private fun UserFormMain() {
    val model: UserFormModel = viewModel(factory = ViewModelProvider.Factory)
    val state by model.state.collectAsState()

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