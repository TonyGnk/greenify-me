package com.example.greenifyme.ui.admin.home

import android.content.Intent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.compose_utilities.ViewModelProvider
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.ui.admin.level.AdminLevelActivity
import com.example.greenifyme.ui.admin.level.AdminLevelModel
import com.example.greenifyme.ui.shared.SharedAppBarType
import com.example.greenifyme.ui.shared.SharedBehavior
import com.example.greenifyme.ui.shared.SharedCard

@Composable
@Preview
fun LevelOfCity() {
    val model: AdminLevelModel = viewModel(factory = ViewModelProvider.Factory)
    val state by model.state.collectAsState()
    val animatedState by model.animatedState.collectAsState()
    val context = LocalContext.current

    SharedCard(
        height = 140.dp,
        topBarType = SharedAppBarType.Enable("Level of City"),
        behavior = SharedBehavior.Clickable {
            context.startActivity(
                Intent(context, AdminLevelActivity::class.java)
            )
        },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp))
            {
                Text(
                    text = getString(state.targetingLevel.levelNameResource),
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "${state.pointsInLevel}/${state.targetPointsInLevel} points",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Image(
                painter = getVector(state.targetingLevel.imageResource),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )
            //SharedProgressBar(animatedState)
        }

    }
}


@Composable
private fun PointsBar(animatedState: Float) {

    val animatedValue by animateFloatAsState(
        targetValue = animatedState,
        animationSpec = tween(
            durationMillis = 900,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.width(126.dp)
        ) {
            Box(modifier = Modifier.weight(animatedValue + 0.0001f))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Text(
                    text = "${(animatedValue * 100).toInt()}%",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 5.dp)
                )
            }
            Box(modifier = Modifier.weight(1 - animatedValue + 0.0001f))
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .width(90.dp)
                .height(10.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            Box(
                modifier = Modifier
                    .weight(animatedValue + 0.0001f)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Box(
                modifier = Modifier
                    .weight(1 - animatedValue + 0.0001f)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.surfaceContainer)
            )
        }
    }
}