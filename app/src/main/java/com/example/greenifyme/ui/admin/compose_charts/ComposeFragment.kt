package com.example.greenifyme.ui.admin.compose_charts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.greenifyme.R


// This a typical fragment like others but in Kotlin. It takes the ComposeView from the common
// view and apply `setViewCompositionStrategy` for optimization (It must destroy when the view is destroyed).
// and apply setContent to set the content of the ComposeView.
// After the setContent we leave behind the XML and we can use the Compose UI
class ComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //In Kotlin we have `val` and `var` to declare variables
        //`val` is like `final` in Java, it means that the variable can't be changed
        //`var` is like a normal variable in Java, it means that the variable can be changed
        val view = inflater.inflate(R.layout.fragment_compose, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)

        //Two last thing for kotlin,
        // 1. It is not necessary to declare the type of the variable when we are initializing it
        // For example, 3 lines above we didn't declare the type of `view` and `composeView`
        // 2. Although we can declare the type of the variable like this: `val nameOfTheVariable: Type`
        // For example, `val string: String`, `val int: Int`, `val boolean: Boolean` or `val list: List<String>`

        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                //`MaterialTheme` is a layer of the Compose UI, everything inside will have same colors, shapes and design rules
                MaterialTheme {
                    // `Box` something like div
                    Box(
                        //Inside the parenthesis we can set the properties of the Box
                        //Modifier is a quick way to set properties
                        modifier = Modifier
                            //In this code first (order matters) the box will be maximized to the size of the
                            //parent and then the background color will be set to the background color of the MaterialTheme
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.background
                            )
                    ) {
                        //Inside the brackets we can set the content of the Box
                        Button(
                            //If you hover the word Button you can see all the properties that you can set
                            //If the parameter you want to set is not in the list add the `modifier`
                            onClick = {
                                print("Hello Compose")
                            }
                        ) {
                            Text("Click me!")
                        }
                    }
                }
            }
        }

        return view
    }
}