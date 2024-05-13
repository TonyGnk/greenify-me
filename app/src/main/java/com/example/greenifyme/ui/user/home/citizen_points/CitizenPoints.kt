package com.example.greenifyme.ui.user.home.citizen_points

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        height = 100.dp
    ) {

        Column(
            modifier = Modifier.padding(20.dp)

        )
        {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 10.dp),
            ) {
                Text(
                    text = state.points.toString() + "/",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Text(
                    text = state.percent.toString() + "%",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(horizontal = 15.dp)
                        
                )



            }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    modifier = Modifier.padding(horizontal = 10.dp),
                ) {
                    Text(
                        text = state.targetPoints.toString(),
                    )
                    LinearProgressIndicator(
                        modifier = Modifier.width(200.dp).height(7.dp),
                        progress = {
                            state.percent/100
                        },

                    )
                }


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