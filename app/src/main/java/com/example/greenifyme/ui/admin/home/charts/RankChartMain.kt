package com.example.greenifyme.ui.admin.home.charts

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.ViewModelProvider
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.ui.admin.rank.AdminRankActivity
import com.example.greenifyme.ui.shared.SharedAppBarType
import com.example.greenifyme.ui.shared.SharedCard
import com.example.greenifyme.ui.shared.SharedCardBehavior


/**
 * Displays the main rank chart UI. Provides interaction to navigate to the admin rank activity.
 */
@Composable
@Preview
fun RankChartMain() {
    val model: RankChartModel = viewModel(factory = ViewModelProvider.Factory)
    val context = LocalContext.current
    SharedCard(
        height = 246.dp,
        topBarType = SharedAppBarType.Enable(getString(model.label)),
        behavior = SharedCardBehavior.Clickable {
            context.startActivity(
                Intent(context, AdminRankActivity::class.java)
            )
        },
    ) {
        RankChartArea(model)
    }
}