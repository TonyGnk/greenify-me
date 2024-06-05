package com.example.greenifyme

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.SharedModelProvider
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.landing_page.LandingPage
import com.example.greenifyme.ui.landing_page.LandingPageViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContent {
            val viewModel: LandingPageViewModel = viewModel(
                factory = SharedModelProvider.Factory(true)
            )
            ComposeTheme {
                LandingPage(viewModel)
            }
        }
    }
}