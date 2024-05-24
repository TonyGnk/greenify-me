package com.example.greenifyme.ui.user.form

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme

class UserFormActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.fragment_compose)
		val composeView = findViewById<ComposeView>(R.id.compose_view)
		composeView.apply {
			setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
			setContent {
				ComposeTheme {
					UserFormMain()
				}
			}
		}
	}
}