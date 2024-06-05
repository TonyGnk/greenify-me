package com.example.greenifyme.ui.admin.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.SharedModelProvider
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.charts.PieChart
import com.example.greenifyme.ui.admin.home.charts.PointDistribution
import com.example.greenifyme.ui.admin.home.charts.RankChart
import com.example.greenifyme.ui.admin.materials.AdminMaterialsActivity
import com.example.greenifyme.ui.admin.notifications.AdminNotificationsActivity
import com.example.greenifyme.ui.shared.SharedLazyColumn
import com.example.greenifyme.ui.shared.tip_of_day.TipOfDay
import com.example.greenifyme.ui.user.form.dialog.CategoriesGrid


class AdminHomeActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val useSampleData = intent.getBooleanExtra("UseSampleData", true)

        setContent {
            ComposeTheme {
                AdminHome(useSampleData)
            }
        }
    }
}

/**
 * Admin Home Screen Composable
 *
 * @param useSampleData Boolean flag to indicate usage of sample data
 */
@Composable
private fun AdminHome(useSampleData: Boolean = false) {
    val model: AdminHomeModel = viewModel(factory = SharedModelProvider.Factory(useSampleData))
    val state by model.state.collectAsState()
    val levelState by model.levelState.collectAsState()
    val tipState by model.tipState.collectAsState()
    val pieState by model.pieState.collectAsState()
    val chartProducerState by model.chartProducerState.collectAsState()
    val categoryPointsList by model.categoryPointsList.collectAsState()
    val animatedCityLevel by model.animatedCityLevelInt.collectAsState()
    val winnersItemList by model.chartRankProducerState.collectAsState()
    val rankWinnersItemList by model.rankWinnersItemList.collectAsState()
    val context = LocalContext.current as Activity


    SharedLazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            AdminHomeAppBar(
                text = getString(state.greetingText),
                onNotificationsIcon = {
                    context.startActivity(
                        Intent(context, AdminNotificationsActivity::class.java).apply {
                            putExtra("UseSampleData", useSampleData)
                        }
                    )
                },
                onMaterials = {
                    context.startActivity(
                        Intent(context, AdminMaterialsActivity::class.java)
                    )
                },
                onExit = { context.finish() }
            )
        }
        item {
            TipOfDay(state = tipState)
        }
        item {
            CityLevel(levelState, animatedCityLevel, useSampleData)
        }
        item {
            PointDistribution(
                getString(model.label), chartProducerState, categoryPointsList
            )
        }
        item {
            RankChart(
                producer = winnersItemList,
                winnersItemList = rankWinnersItemList,
                label = getString(model.rankLabel),
                useSampleData = useSampleData
            )
        }
        item {
            PieChart(
                listOfPiecesWithNames = pieState.percentOfMaterials,
                label = getString(pieState.selectedCategory.description),
                onSelectButtonClicked = { model.setPieChartDialog(true) }
            )
        }
    }

    if (pieState.dialogOpened) {
        PieChartDialog({ model.setPieChartDialog(false) }) {
            CategoriesGrid({ model.onCategorySelectedPieChart(it) }, pieState.recyclingCategories)
        }
    }
}

/**
 * Pie Chart Dialog Composable
 *
 * @param onDismiss Lambda function to handle dismiss action
 * @param content Composable content for the dialog
 */
@Composable
fun PieChartDialog(onDismiss: () -> Unit={}, content: @Composable ColumnScope.() -> Unit = {}) {
    Dialog(onDismiss) {
        Column(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.surfaceContainer,
                    RoundedCornerShape(getDimen(R.dimen.column_card_corner_radius))
                )
                .padding(16.dp)
        ) {
            Text(
                text = "Select",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(14.dp))
            content()
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}