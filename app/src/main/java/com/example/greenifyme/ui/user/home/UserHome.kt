package com.example.greenifyme.ui.user.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.user.form.UserFormActivity
import com.example.greenifyme.ui.user.home.app_bar.UserHomeAppBar
import com.example.greenifyme.ui.user.home.citizen_points.CitizenPoints

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserHome() {
    val model: UserHomeModel = viewModel(factory = ViewModelProvider.Factory)
    val state by model.state.collectAsState()
    val pointState by model.pointState.collectAsState()
    val context = LocalContext.current as Activity

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
        modifier = Modifier.fillMaxSize(),
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
        LazyColumn(
            contentPadding = PaddingValues(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 2.dp)
        ) {
            item {
                UserHomeAppBar(state)
            }
            item {
                CitizenPoints(pointState)
            }
        }
    }
}

@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        UserHome()
    }
}