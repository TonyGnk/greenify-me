package com.example.greenifyme.ui.user.home.citizen_points

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.shared.DefaultCard
import com.example.greenifyme.ui.user.home.UserPointState

@Composable
fun CitizenPoints(
    state: UserPointState = UserPointState(),
) {
    DefaultCard(
        horizontalPadding = dimensionResource(id = R.dimen.horizontalScreenPadding),
        height = 250.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = state.points.toString(),
            )
            Text(
                text = state.targetPoints.toString(),
            )
            Text(
                text = state.percent.toString(),
            )
        }
    }

}


@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        CitizenPoints()
    }
}