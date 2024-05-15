package com.example.greenifyme.ui.user.form

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.admin.home.app_bar.AnimatedGreeting
import com.example.greenifyme.ui.user.home.app_bar.UserHomeAppBar
import com.example.greenifyme.ui.user.home.citizen_points.CitizenPoints

@Composable
fun UserForm() {
    val model: UserFormModel = viewModel(factory = ViewModelProvider.Factory)
    //val state by model.adminHomeState.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
        modifier = Modifier.fillMaxSize() //We want the surface to fill the entire screen
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 2.dp)
        ) {
            item {
                UserFormAppBar(model)
            }
//            item {
//                CitizenPoints(pointState)
//            }
        }
    }
}

@Composable
fun UserFormAppBar(
    model: UserFormModel = viewModel(factory = ViewModelProvider.Factory)
) {
    val context = LocalContext.current as Activity

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.horizontalScreenPadding)),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        //AnimatedGreeting(state.greetingText)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                context.finish()
            },
            content = {
                Icon(
                    painterResource(id = R.drawable.baseline_exit_to_app_24),
                    contentDescription = "Exit"
                )
            }
        )
    }

}

@Composable
fun UserFormContent(
    model: UserFormModel = viewModel(factory = ViewModelProvider.Factory)
) {

}