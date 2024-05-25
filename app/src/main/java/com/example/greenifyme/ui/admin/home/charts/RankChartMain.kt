package com.example.greenifyme.ui.admin.home.charts

import android.content.Intent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.navigation.ViewModelProvider
import com.example.greenifyme.ui.admin.rank.AdminRankActivity
import com.example.greenifyme.ui.shared.SharedCard


/**
 * Displays the main rank chart UI. Provides interaction to navigate to the admin rank activity.
 */
@Composable
@Preview
fun RankChartMain() {
    val model: RankChartModel = viewModel(factory = ViewModelProvider.Factory)
    val context = LocalContext.current
    SharedCard(height = 236.dp) {
        SharedChartLayout {
            SharedChartTopBar(model.label) {
                TextButton(
                    onClick = {
                        context.startActivity(
                            Intent(context, AdminRankActivity::class.java)
                        )
                    },
                    modifier = Modifier.height(34.dp)
                ) {
                    Text(getString(model.score))
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        getVector(drawableValue = R.drawable.rectangle_list),
                        contentDescription = "All Users",
                    )
                }
            }
            RankChartArea(model)
        }
    }
}




