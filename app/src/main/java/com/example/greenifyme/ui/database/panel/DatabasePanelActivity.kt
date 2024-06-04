package com.example.greenifyme.ui.database.panel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.SharedModelProvider
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.database.manager.DatabaseManagerActivity
import com.example.greenifyme.ui.shared.SharedAnimatedList
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedBackBehavior
import com.example.greenifyme.ui.shared.SharedCard
import com.example.greenifyme.ui.shared.SharedCardText
import com.example.greenifyme.ui.shared.SharedColumn
import com.example.greenifyme.ui.shared.SharedLazyColumn
import kotlinx.coroutines.flow.MutableStateFlow

class DatabasePanelActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme { DatabasePanelScreen() }
        }
    }
}


/**
 * Main screen composable for the Database Panel.
 * @param model ViewModel for managing UI state and actions.
 */
@Composable
private fun DatabasePanelScreen(
    model: DatabasePanelModel = viewModel(factory = SharedModelProvider.Factory(true))
) {
    val context = LocalContext.current as Activity
    val isVisibleState = remember { MutableStateFlow(false) }
    val isVisible by isVisibleState.collectAsState()
    val toastMessage by model.toastMessage.collectAsState()
    val state by model.state.collectAsState()

    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            model.clearToastMessage()
        }
    }

    LaunchedEffect(true) {
        isVisibleState.value = true
    }

    DatabasePanelContent(
        isVisible = isVisible,
        state = state,
        onBack = context::finish,
        onShowDialog = model::showDialog,
        onDismissDialog = model::onDismissDialog,
        onConfirmDialog = model::onConfirmDialog
    )
}

/**
 * Composable function that holds the main UI structure.
 * @param isVisible Visibility state of the list.
 * @param state Current UI state.
 * @param onBack Back button click handler.
 * @param onShowDialog Function to show dialog.
 * @param onDismissDialog Function to dismiss dialog.
 * @param onConfirmDialog Function to confirm dialog action.
 */
@Composable
private fun DatabasePanelContent(
    isVisible: Boolean,
    state: DBPanelState,
    onBack: () -> Unit,
    onShowDialog: (DBPanelAction) -> Unit,
    onDismissDialog: () -> Unit,
    onConfirmDialog: (DBPanelAction?, Context) -> Unit
) {
    SharedColumn(applyHorizontalPadding = false) {
        DatabasePanelAppBar(onBack)
        SharedAnimatedList(isVisible) {
            SharedLazyColumn(applyHorizontalPadding = false) {
                item { DatabaseStatus() }
                item { DatabaseActions(onShowDialog) }
            }
        }
    }

    if (state.showDialog) {
        DatabaseActionDialog(
            onDismiss = onDismissDialog,
            onConfirm = onConfirmDialog,
            action = state.selectedAction
        )
    }
}

/**
 * AppBar composable for the Database Panel screen.
 * @param onBack Function to handle back button click.
 */
@Composable
private fun DatabasePanelAppBar(onBack: () -> Unit) {
    SharedAppBar(
        text = getString(R.string.database_panel_database_panel),
        backBehavior = SharedBackBehavior.Enable(onBack)
    )
}

/**
 * Composable to display the database status.
 */
@Composable
private fun DatabaseStatus() {
    val context = LocalContext.current
    val intentNormalDBManager = Intent(context, DatabaseManagerActivity::class.java).apply {
        putExtra("UseSampleData", false)
    }
    val intentSampleDBManager = Intent(context, DatabaseManagerActivity::class.java).apply {
        putExtra("UseSampleData", true)
    }

    Column {
        SharedCardText(getString(R.string.database_panel_status), Modifier.padding(14.dp))
        SharedCard(
            modifierContent = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            DatabaseContent(getString(R.string.database_panel_main_database)) {
                context.startActivity(
                    intentNormalDBManager
                )
            }
            DatabaseContent(getString(R.string.database_panel_sample_database)) {
                context.startActivity(
                    intentSampleDBManager
                )
            }
        }
    }
}

/**
 * Composable to display the database content with a button.
 * @param name Name of the database.
 * @param onClick Click handler for the button.
 */
@Composable
private fun DatabaseContent(name: String, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(name)
        Button(onClick) {
            Text(getString(R.string.database_panel_inspect))
        }
    }
}

/**
 * Composable to display the database actions.
 * @param showDialog Function to show the action dialog.
 */
@Composable
private fun DatabaseActions(showDialog: (DBPanelAction) -> Unit) {
    Column {
        SharedCardText(getString(R.string.database_panel_actions), Modifier.padding(14.dp))
        SharedCard(
            modifierContent = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            ActionContent(getString(R.string.database_panel_main_database)) {
                IconButton(onClick = { showDialog(DBPanelAction.RESET_MAIN) }) {
                    Icon(
                        painter = getVector(
                            R.drawable.rotate_left
                        ), contentDescription = getString(R.string.database_panel_reset),
                        modifier = Modifier.size(17.dp)
                    )
                }
                TextButton({ showDialog(DBPanelAction.DELETE_MAIN) }) {
                    Text(getString(R.string.database_panel_delete_all))
                }
            }
            ActionContent(getString(R.string.database_panel_sample_database)) {
                IconButton(onClick = { showDialog(DBPanelAction.RESET_SAMPLE) }) {
                    Icon(
                        painter = getVector(
                            R.drawable.rotate_left
                        ), contentDescription = getString(R.string.database_panel_reset),
                        modifier = Modifier.size(17.dp)
                    )
                }
                TextButton({ showDialog(DBPanelAction.DELETE_SAMPLE) }) {
                    Text(getString(R.string.database_panel_delete_all))
                }
            }
        }
    }
}

/**
 * Composable to display the content for actions.
 * @param name Name of the action.
 * @param content Composable content to display the actions.
 */
@Composable
private fun ActionContent(name: String, content: @Composable RowScope.() -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(name)
        Spacer(Modifier.weight(1f))
        content()
    }
}

/**
 * Dialog composable for displaying action confirmation.
 * @param onDismiss Function to handle dismiss action.
 * @param onConfirm Function to handle confirm action.
 * @param action The action to be confirmed.
 */
@Composable
private fun DatabaseActionDialog(
    onDismiss: () -> Unit,
    onConfirm: (DBPanelAction?, Context) -> Unit,
    action: DBPanelAction?
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = getString(R.string.database_panel_are_you_sure))
        },
        text = {
            Text(
                when (action) {
                    DBPanelAction.DELETE_MAIN -> getString(R.string.database_panel_delete_main_confirmation)
                    DBPanelAction.DELETE_SAMPLE -> getString(R.string.database_panel_delete_sample_confirmation)
                    DBPanelAction.RESET_SAMPLE -> getString(R.string.database_panel_reset_sample_confirmation)
                    DBPanelAction.RESET_MAIN -> getString(R.string.database_panel_reset_main_confirmation)
                    null -> ""
                }
            )
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(getString(R.string.database_panel_cancel))
            }
        },
        confirmButton = {
            Button({ onConfirm(action, context) }) {
                Text(getString(R.string.database_panel_database_panel_ok))
            }
        },
        modifier = Modifier.widthIn(450.dp)
    )
}

@Composable
@Preview
private fun AppBarPreview() {
    ComposeTheme { DatabasePanelAppBar(onBack = {}) }
}

@Composable
@Preview
private fun StatusPreview() {
    ComposeTheme { DatabaseStatus() }
}

@Composable
@Preview
private fun ActionsPreview() {
    ComposeTheme { DatabaseActions(showDialog = {}) }
}

@Composable
@Preview
private fun DialogPreview() {
    ComposeTheme {
        DatabaseActionDialog(
            onDismiss = {},
            onConfirm = { _, _ -> },
            action = DBPanelAction.DELETE_MAIN
        )
    }
}