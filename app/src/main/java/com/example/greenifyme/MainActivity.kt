package com.example.greenifyme

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.landing_page.LandingPage


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