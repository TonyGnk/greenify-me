package com.example.greenifyme.ui.landing_page

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.SharedModelProvider
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.AdminHomeActivity
import com.example.greenifyme.ui.database.panel.DatabasePanelActivity
import com.example.greenifyme.ui.login.LoginNavigationActivity
import com.example.greenifyme.ui.shared.SharedColumn

@Composable
fun LandingPage() {
    val model: LandingPageViewModel = viewModel(factory = SharedModelProvider.Factory(true))
    model.initialize()
    val state by model.uiState.collectAsState()

    SharedColumn(
        verticalArrangement = Arrangement.spacedBy(0.dp),
        applyHorizontalPadding = false
    ) {
        TopSection(model::onInfoClicked, Modifier.weight(1f))
        MiddleSection()
        BottomSection(Modifier.weight(1f))
    }

    if (state.showDialog) Dialog(model::dialogDismiss)
}

@Composable
private fun TopSection(
    onInfoClicked: () -> Unit = {}, modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
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
                    contentDescription = getString(R.string.landing_page_info),
                    tint = Color.Gray,
                    modifier = Modifier.size(23.dp)
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_landing_launcher_foreground),
            contentDescription = stringResource(id = R.string.logoDescription),
            modifier = Modifier.size(110.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = getString(R.string.landing_page_welcome),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
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
        Text(
            text = getString(R.string.landing_page_select_option),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { context.startActivity(intentNormalLogin) },
            modifier = Modifier.widthIn(min = 260.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "",
                modifier = Modifier.size(15.dp)
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
                contentDescription = "",
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(getString(R.string.landing_page_admin_panel), Modifier.padding(10.dp))
        }
    }
}

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
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape),
        ) {
            TextButton(onClick = {
                context.startActivity(intentSampleDBManager)
            }, modifier = Modifier.padding(top = 18.dp, bottom = 18.dp, start = 18.dp)) {
                Icon(
                    painter = getVector(R.drawable.back_up), contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Database Panel", color = MaterialTheme.colorScheme.onSurface)
            }
            TextButton(onClick = {
                context.startActivity(intentSampleAdmin)
            }, modifier = Modifier.padding(top = 18.dp, bottom = 18.dp, end = 18.dp)) {
                Icon(
                    painter = getVector(R.drawable.chart_user), contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Presentation", color = MaterialTheme.colorScheme.onSurface)
            }
        }
        //DraggableHandleBox(intentSampleAdmin, intentSampleDBManager)
        Spacer(Modifier.height(4.dp))
    }
}


@Composable
private fun Dialog(onDismiss: () -> Unit = {}) {
    val mikevafeiad045 = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mikevafeiad045"))
    val marinagial = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/marinagial"))
    val tonyGnk = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TonyGnk"))
    val soly02 = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/soly-02"))
    val mppapad = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mppapad"))

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = getString(R.string.landing_page_app_information)) },
        text = {
            Column {
                Text(
                    text = getString(R.string.landing_page_greenify_me)
                )
                Spacer(modifier = Modifier.height(12.dp))
                DeveloperRow("mikevafeiad045", true, mikevafeiad045)
                DeveloperRow("marinagial", false, marinagial)
                DeveloperRow("TonyGnk", true, tonyGnk)
                DeveloperRow("mppapad", true, mppapad)
                DeveloperRow("soly-02", false, soly02)

            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss
            ) {
                Text(getString(R.string.landing_page_ok))
            }
        }
    )
}

@Composable
fun DeveloperRow(name: String, isMan: Boolean, intent: Intent) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(CircleShape)
            .clickable { context.startActivity(intent) },
    ) {
        Icon(
            painter = getVector(drawableValue = if (isMan) R.drawable.man_head else R.drawable.woman_head),
            contentDescription = name,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(37.dp)
                .padding(8.dp)
        )
        Text(name, modifier = Modifier.padding(8.dp))
    }
}

@Composable
@Preview
fun TopSectionPreview() {
    ComposeTheme { TopSection({}, Modifier) }
}

@Composable
@Preview
fun MiddleSectionPreview() {
    ComposeTheme { MiddleSection() }
}


@Composable
@Preview
fun BottomSectionPreview() {
    ComposeTheme { BottomSection(Modifier) }
}


@Composable
@Preview
fun LandingDialogPreview() {
    ComposeTheme { Dialog() }
}