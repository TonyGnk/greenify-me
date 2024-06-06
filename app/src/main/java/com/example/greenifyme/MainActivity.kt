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
            /*Create the ViewModel for the landing page using a factory method from SharedModelProvider.
             * We do this instead of using the default way ("viewModel = LandingPageViewModel()") because
             * the ViewModel needs instances of databases that are set up during the application's initialization.
             *
             * SharedModelProvider.Factory is a custom factory class that helps us create the ViewModel with
             * the necessary dependencies. By using this factory, we can initialize the databases once in
             * the ApplicationSetup and then share them with the ViewModel. This approach reduces boilerplate
             * code and ensures that the ViewModel has everything it needs to function properly.
             *
             * The ViewModel pattern helps in managing UI-related data in a lifecycle-conscious way.
             * It allows data to survive configuration changes such as screen rotations.
             */
            val viewModel: LandingPageViewModel = viewModel(
                factory = SharedModelProvider.Factory(true)
            )
            // Set the theme for the composables within the app. ComposeTheme is a custom theme
            // that applies the styles and color schemes defined for this app in compose_utilities.theme package.
            ComposeTheme {
                LandingPage(viewModel)
            }
        }
    }
}