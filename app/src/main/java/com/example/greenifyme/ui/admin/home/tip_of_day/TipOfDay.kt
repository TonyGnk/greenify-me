package com.example.greenifyme.ui.admin.home.tip_of_day

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.model.AdminHomeModel
import com.example.greenifyme.ui.admin.home.model.AdminTipState

@Composable
fun TipOfDay(
    model: AdminHomeModel = AdminHomeModel(),
    state: AdminTipState = AdminTipState(selectedTip= R.string.recycling_tip_1),
    horizontalPadding: Dp = 12.dp
) {
    Text(
        text = stringResource(state.selectedTip),
        modifier = Modifier.padding(horizontal = horizontalPadding)
    )
}


@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        TipOfDay()
    }
}