package com.example.greenifyme.ui.admin.home

import android.content.Intent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.ui.admin.level.AdminLevelActivity
import com.example.greenifyme.ui.admin.level.CityLevelStep
import com.example.greenifyme.ui.shared.SharedAppBarType
import com.example.greenifyme.ui.shared.SharedBehavior
import com.example.greenifyme.ui.shared.SharedCard

@Composable
@Preview
fun CityLevel(
    levelState: CityLevelStep = CityLevelStep(points = 2500),
    animatedCityLevel: Int = 0,
    useSampleData: Boolean = false
) {
    val context = LocalContext.current

    val animatedValue by animateIntAsState(
        targetValue = animatedCityLevel,
        animationSpec = tween(
            durationMillis = 800,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    SharedCard(
        height = 140.dp,
        topBarType = SharedAppBarType.Enable(
            getString(stringValue = R.string.admin_level_of_city_title)
        ),
        behavior = SharedBehavior.Clickable {
            context.startActivity(
                Intent(context, AdminLevelActivity::class.java).apply {
                    putExtra("UseSampleData", useSampleData)
                }
            )
        },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = getString(levelState.targetingLevel.levelNameResource),
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "${animatedValue}/${levelState.targetPointsInLevel} ${
                        getString(
                            stringValue = R.string.admin_level_of_city_points
                        )
                    }",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Image(
                painter = getVector(levelState.targetingLevel.imageResource),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )
        }

    }
}