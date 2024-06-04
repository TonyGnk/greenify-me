package com.example.greenifyme.ui.admin.home.charts

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.greenifyme.data.relations.WinnerItem
import com.example.greenifyme.ui.admin.rank.AdminRankActivity
import com.example.greenifyme.ui.shared.SharedAppBarType
import com.example.greenifyme.ui.shared.SharedBehavior
import com.example.greenifyme.ui.shared.SharedCard
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer


@Composable
fun RankChart(
    producer: CartesianChartModelProducer,
    winnersItemList: List<WinnerItem>,
    label: String,
    useSampleData: Boolean,
) {
    val context = LocalContext.current
    SharedCard(
        height = 246.dp, //DO NOT CHANGE
        topBarType = SharedAppBarType.Enable(label),
        behavior = if (winnersItemList.isEmpty()) SharedBehavior.Static
        else SharedBehavior.Clickable {
            context.startActivity(
                Intent(context, AdminRankActivity::class.java).apply {
                    putExtra("UseSampleData", useSampleData)
                }
            )
        },
    ) {
        RankChartArea(producer, winnersItemList)
    }
}