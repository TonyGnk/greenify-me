package com.example.greenifyme.ui.user.home

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.ui.admin.rank.AdminRankActivity
import com.example.greenifyme.ui.shared.SharedAppBarType
import com.example.greenifyme.ui.shared.SharedBehavior
import com.example.greenifyme.ui.shared.SharedCard
import com.example.greenifyme.ui.shared.SharedProgressBar

/**
 * This composable represents the Citizen Points section.
 *
 * @param model The view model to use for state management.
 */
@Composable
fun CitizenPoints(
    model: UserHomeModel, useSampleData: Boolean
) {
    val state by model.pointState.collectAsState()
    val animatedState by model.animatedState.collectAsState()
    val context = LocalContext.current

    SharedCard(
        topBarType = SharedAppBarType.Enable(
            getString(R.string.user_points_label)
        ),
        applyHorizontalPadding = false,
        behavior = SharedBehavior.Clickable(
            onClick = {
                context.startActivity(
                    Intent(context, AdminRankActivity::class.java).apply {
                        putExtra("UseSampleData", useSampleData)
                    }
                )
            }
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp))
            {
                Text(
                    text = state.points.toString(),
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "${state.pointsInLevel}/${state.targetPointsInLevel} ${getString(R.string.admin_level_of_city_points)}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            SharedProgressBar(
                percent = animatedState,
                startingLabel = (state.targetingLevel.order - 1).toString(),
                endingLabel = state.targetingLevel.order.toString(),
            )
        }
    }
}