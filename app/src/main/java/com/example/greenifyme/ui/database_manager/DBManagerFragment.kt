package com.example.greenifyme.ui.database_manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme

class DBManagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Store the view in a variable
        val view = inflater.inflate(R.layout.fragment_database_manager, container, false)

        // Store the ComposeView in a variable
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            //For optimization - Copy-Paste every time
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                //Apply default style and colors
                ComposeTheme {
                    DBManagerNavigation()
                }
            }
        }
        return view
    }
}