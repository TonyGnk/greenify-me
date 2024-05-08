package com.example.greenifyme.ui.admin.home.tip_of_day

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.model.AdminHomeAppBarModel
import com.example.greenifyme.ui.admin.home.model.AdminTipState

@Composable
fun TipOfDay(
    model: AdminHomeAppBarModel = AdminHomeAppBarModel(),
    state: AdminTipState = AdminTipState(),
) {
    Text(stringResource(state.selectedTip))
}


@Preview
@Composable
private fun ComposablePreview() {
    ComposeTheme {
        TipOfDay()
    }
}