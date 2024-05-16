package com.example.greenifyme.ui.user

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.user.home.UserHome

class UserHomeActivity : AppCompatActivity() {
	public override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.fragment_compose_user)
		val composeView = findViewById<ComposeView>(R.id.compose_view)
		composeView.apply {
			setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
			setContent {
				// Apply default style and colors
				ComposeTheme {
					UserHome()
				}
			}
		}
	}
}