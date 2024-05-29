package com.example.greenifyme.ui.database_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.greenifyme.compose_utilities.theme.ComposeTheme

class DBManagerActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Apply default style and colors
            ComposeTheme {
                DBManagerNavigation()
            }
        }
    }
}