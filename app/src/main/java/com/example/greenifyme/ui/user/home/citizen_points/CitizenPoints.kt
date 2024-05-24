package com.example.greenifyme.ui.user.home.citizen_points

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        height = 120.dp
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,

            )
        {


            Row(
                horizontalArrangement = Arrangement.End,
            )
            {
                Text( //to pososto toy completion, Na ginei dynamiko panw apo thn mpara
                    modifier = Modifier.padding(horizontal = 11.dp),
                    text = state.percent.toString() + "%",
                    fontSize = 10.sp,
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(80.dp),

            ) {
                Text( // Pontoi poy exei mazepsei o xristis
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = state.points.toString() ,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                LinearProgressIndicator( // to progress bar
                    modifier = Modifier.width(120.dp)
                        .height(28.dp)
                        .padding(0.dp,10.dp) ,
                    progress = {
                        state.percent/100
                    },

                    )


            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(80.dp),

            ) {
                Text( // Pontoi poy stoxevei na ftasei o xrhsths gia na paei sto epomeno epipedo
                    text = "/" + state.targetPoints.toString(),
                    modifier = Modifier.padding(horizontal = 11.dp),
                )

                Text(

                    text = "9", // trexwn epipedo, an ginetai na ftiaxtei kapws to
                    //spacing, egw prospathisa alla den ta katafera... eidika otan paei px level 99 pros 100
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape)
                        .padding(5.dp)

                )
                Text(
                    text = "10" , // epomeno epipedo,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape)
                        .padding(5.dp)
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