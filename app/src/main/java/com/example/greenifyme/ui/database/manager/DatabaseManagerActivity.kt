package com.example.greenifyme.ui.database.manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.database.manager.navigation.ManagerNavigation

class DatabaseManagerActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val useSampleData = intent.getBooleanExtra("UseSampleData", true)

        setContent {
            ComposeTheme {
                ManagerNavigation(useSampleData)
            }
        }
    }
}