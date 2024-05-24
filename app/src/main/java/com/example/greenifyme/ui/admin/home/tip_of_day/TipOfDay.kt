package com.example.greenifyme.ui.admin.home.tip_of_day

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.model.AdminTipState

@Composable
@Preview
fun TipOfDay(
    state: AdminTipState = AdminTipState(selectedTip = R.string.recycling_tip_1),
    horizontalPadding: Dp = 12.dp
) {
    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.md_theme_dark_onPrimary),
                shape = CircleShape,
            )
            .wrapContentSize(Alignment.Center)
            .padding(horizontal = horizontalPadding, vertical = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Tip of the Day",
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.md_theme_dark_onPrimaryContainer),
                modifier = Modifier.padding(horizontal = horizontalPadding, vertical = 5.dp)
            )
            Text(
                text = stringResource(state.selectedTip),
                modifier = Modifier.padding(horizontal = horizontalPadding),
                color = colorResource(id = R.color.md_theme_dark_onPrimaryContainer),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

}