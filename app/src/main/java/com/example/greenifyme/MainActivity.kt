package com.example.greenifyme

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenifyme.compose_utilities.NotificationHandler
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.AdminHomeActivity
import com.example.greenifyme.ui.database_manager.DBManagerActivity
import com.example.greenifyme.ui.login.LoginNavigationActivity
import com.example.greenifyme.ui.shared.SharedColumn
import com.example.greenifyme.ui.user.UserHomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContent {
            ComposeTheme {
                LandingPage()
            }
        }
    }
}

//R.string.select_label
@Composable
@Preview
private fun LandingPage() {
    val context = LocalContext.current
    SharedColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        addSurfaceColor = false,
        applyHorizontalPadding = true,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(6f))
        Text(
            text = getString(R.string.select_label),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = getString(R.string.select_description)
        )
        Spacer(modifier = Modifier.weight(2f))
        FilledTonalButton(
            onClick = {
                context.startActivity(Intent(context, LoginNavigationActivity::class.java))
            }, modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(bottom = 3.dp)
        ) {
            Text(text = stringResource(id = R.string.database_team))
        }

        FilledTonalButton(
            onClick = {
                context.startActivity(Intent(context, UserHomeActivity::class.java))
            }, modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(bottom = 3.dp)
        ) {
            Text(text = stringResource(id = R.string.citizen_page))
        }
        FilledTonalButton(
            onClick = {
                context.startActivity(Intent(context, AdminHomeActivity::class.java))
            }, modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(bottom = 3.dp)
        ) {
            Text(text = stringResource(id = R.string.admin_page_compose))
        }
        FilledTonalButton(
            onClick = {
                context.startActivity(Intent(context, DBManagerActivity::class.java))
            }, modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(text = getString(R.string.database_manager))
        }
        Spacer(modifier = Modifier.weight(9f))
    }
}
