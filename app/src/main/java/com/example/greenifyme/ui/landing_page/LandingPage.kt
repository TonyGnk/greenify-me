package com.example.greenifyme.ui.landing_page

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.ui.admin.home.AdminHomeActivity
import com.example.greenifyme.ui.database.panel.DatabasePanelActivity
import com.example.greenifyme.ui.login.LoginNavigationActivity
import com.example.greenifyme.ui.shared.SharedColumn

/**
 * @param model ViewModel for the LandingPage
 */
@Composable
fun LandingPage(model: LandingPageViewModel = viewModel()) {
    model.initialize()
    val state by model.uiState.collectAsState()

    SharedColumn(
        verticalArrangement = Arrangement.spacedBy(0.dp),
        applyHorizontalPadding = true
    ) {
        TopSection(model::onInfoClicked, Modifier.weight(1f))
        MiddleSection()
        BottomSection(Modifier.weight(1f))
    }

    if (state.showDialog) Dialog(model::dialogDismiss)
}

/**
 * @param onInfoClicked Callback when the info icon is clicked
 * @param modifier Modifier to be applied to the composable
 */
@Composable
private fun TopSection(onInfoClicked: () -> Unit = {}, modifier: Modifier) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val screenHeight = maxHeight
        val imageSize = screenHeight * 0.33f

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onInfoClicked,
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.info),
                        contentDescription = getString(R.string.landing_page_dialog_app_information),
                        tint = Color.Gray,
                        modifier = Modifier.size(23.dp)
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_landing_launcher_foreground),
                contentDescription = stringResource(id = R.string.logoDescription),
                modifier = Modifier.size(imageSize)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = getString(R.string.landing_page_welcome),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun MiddleSection() {
    val context = LocalContext.current
    val intentNormalAdmin = Intent(context, AdminHomeActivity::class.java).apply {
        putExtra("UseSampleData", false)
    }
    val intentNormalLogin = Intent(context, LoginNavigationActivity::class.java)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = getString(R.string.landing_page_login))
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { context.startActivity(intentNormalLogin) },
            modifier = Modifier.widthIn(min = 260.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.user),
                contentDescription = getString(R.string.landing_page_des_citizen_icon),
                modifier = Modifier.size(17.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(getString(R.string.landing_page_citizen_login), Modifier.padding(10.dp))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { context.startActivity(intentNormalAdmin) },
            modifier = Modifier.widthIn(min = 260.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.admin_alt),
                contentDescription = getString(R.string.landing_page_des_admin_icon),
                modifier = Modifier.size(17.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(getString(R.string.landing_page_admin), Modifier.padding(10.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BottomSection(modifier: Modifier) {
    val context = LocalContext.current
    val intentSampleAdmin = Intent(context, AdminHomeActivity::class.java).apply {
        putExtra("UseSampleData", true)
    }
    val intentSampleDBManager = Intent(context, DatabasePanelActivity::class.java).apply {
        putExtra("UseSampleData", true)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier,
    ) {
        // Text(text = getString(R.string.landing_page_or))
        Box(
            modifier = Modifier
                .padding(vertical = getDimen(dimenValue = R.dimen.horizontalScreenPadding))
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), RoundedCornerShape(
                        getDimen(dimenValue = R.dimen.column_card_corner_radius)
                    )
                )
                .widthIn(max = 520.dp),
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            ) {
                TextButton(
                    onClick = { context.startActivity(intentSampleDBManager) },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = getVector(R.drawable.back_up),
                        contentDescription = getString(R.string.landing_page_des_db_icon),
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = getString(R.string.landing_page_db_manager),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                TextButton(
                    onClick = { context.startActivity(intentSampleAdmin) },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = getVector(R.drawable.chart_user),
                        contentDescription = getString(R.string.landing_page_des_presentation_icon),
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = getString(R.string.landing_page_admin_panel),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}


/**
 * @param onDismiss Callback when the dialog is dismissed
 */
@Composable
private fun Dialog(onDismiss: () -> Unit = {}) {
    val mikevafeiad045 = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mikevafeiad045"))
    val marinagial = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/marinagial"))
    val tonyGnk = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TonyGnk"))
    val soly02 = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/soly-02"))
    val mppapad = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mppapad"))

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = getString(R.string.landing_page_dialog_app_information)) },
        text = {
            LazyColumn {
                item {
                    Text(
                        text = getString(R.string.landing_page_greenify_me)
                    )
                }
                item { Spacer(modifier = Modifier.height(18.dp)) }
                item { DeveloperRow("mikevafeiad045", true, mikevafeiad045) }
                item { DeveloperRow("marinagial", false, marinagial) }
                item { DeveloperRow("TonyGnk", true, tonyGnk) }
                item { DeveloperRow("mppapad", true, mppapad) }
                item { DeveloperRow("soly-02", false, soly02) }
            }
        },
        confirmButton = { }
    )
}

/**
 * @param name Developer's name
 * @param isMan Gender indicator for the icon
 * @param intent Intent to the developer's profile
 */
@Composable
fun DeveloperRow(name: String, isMan: Boolean, intent: Intent) {
    val context = LocalContext.current
    TextButton(
        onClick = { context.startActivity(intent) },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.height(40.dp)
    ) {
        Icon(
            painter = getVector(drawableValue = if (isMan) R.drawable.man_head else R.drawable.woman_head),
            contentDescription = name,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(8.dp)
        )
        Text(name, modifier = Modifier.padding(8.dp))
    }
}


@Composable
@Preview(showBackground = true)
fun LandingPagePreview() {
    SharedColumn(
        verticalArrangement = Arrangement.spacedBy(0.dp),
        applyHorizontalPadding = true
    ) {
        TopSection(modifier = Modifier.weight(1f))
        MiddleSection()
        BottomSection(Modifier.weight(1f))
    }
}