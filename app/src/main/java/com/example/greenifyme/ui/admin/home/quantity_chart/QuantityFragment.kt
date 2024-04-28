package com.example.greenifyme.ui.admin.home.quantity_chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme


class QuantityFragment : Fragment() {

    // This a typical fragment like others but in Kotlin
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
            // For optimization (It must destroy when the view is destroyed).
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            // We leave behind the XML and we can use the Compose UI
            setContent {
                //`MaterialTheme` is a layer of the Compose UI, everything inside will have same colors, shapes and design rules
                ComposeTheme {
                    QuantityMain()
                }
            }
        }
        return view
    }
}

@Composable
fun QuantityMain() {
    Surface(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(12),
        modifier = Modifier
            .padding(10.dp) //Padding is Margin most cases
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(9.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Συνολική Ποσότητα", style = MaterialTheme.typography.headlineSmall)
                MultiChoiceSegmented()
            }
            QuantityChartArea()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiChoiceSegmented() {
    val checkedList = remember { mutableIntStateOf(1) }
    val options = listOf("Τύποι", "Υλικά")
    MultiChoiceSegmentedButtonRow(
        modifier = Modifier
            .height(29.dp)
            .width(140.dp),
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size,
                ),

                icon = {
                    if (index == checkedList.intValue) {
                        SegmentedButtonDefaults.Icon(
                            active =
                            //false
                            index == checkedList.intValue
                        )
                    }
                },
                onCheckedChange = {
                    if (index != checkedList.intValue) {
                        checkedList.intValue = index
                    }
                },
                checked = index == checkedList.intValue
            ) {
                Text(label, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Composable
fun QuantityChartArea() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = RoundedCornerShape(12),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Σύντομα Διαθέσιμο")
        }
    }
}

@Preview(widthDp = 402, heightDp = 333)
@Composable
fun QuantityMainPreview() {
    ComposeTheme(dynamicColor = false) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            QuantityMain()
        }
    }
}