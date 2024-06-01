package com.example.greenifyme.ui.admin.materials

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greenifyme.R
import com.example.greenifyme.compose_utilities.ViewModelProvider
import com.example.greenifyme.compose_utilities.getDimen
import com.example.greenifyme.compose_utilities.getVector
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.data.Both
import com.example.greenifyme.data.Grams
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Pieces
import com.example.greenifyme.ui.shared.SharedAppBar
import com.example.greenifyme.ui.shared.SharedBackBehavior
import com.example.greenifyme.ui.shared.SharedColumn

class AdminMaterialsActivity : ComponentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //Apply default style and colors
            ComposeTheme {
                AdminMaterials()
            }
        }
    }
}

@Composable
@Preview
private fun AdminMaterials() {
    val model: AddingMaterialsModel = viewModel(factory = ViewModelProvider.Factory)
    val materials by model.totalMaterialsState.collectAsState()
    val activity = LocalContext.current as Activity


    SharedColumn(
        applyHorizontalPadding = false,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    painter = getVector(drawableValue = R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    ) {
        SharedAppBar(
            text = "Materials",
            backBehavior = SharedBackBehavior.Enable { activity.finish() },
        )
        AdminRankGrid(materials)


    }
}

@Composable
private fun AdminRankGrid(materials: List<Material>) {
    LazyColumn(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.surfaceContainerLowest,
            shape = RoundedCornerShape(6)
        )
    ) {
        itemsIndexed(materials) { index, item ->
            AccountListItem2(item, index = index)
        }
    }
}

@Composable
private fun AccountListItem2(material: Material, index: Int) {
    val pointsLine: String = when (material.type) {
        is Pieces -> "${material.type.pointsPerPiece} per piece"
        is Grams -> "${material.type.pointsPerGram} per gram"
        is Both -> "${material.type.pointsPerPiece} per piece, ${material.type.pointsPerGram} per gram"
    }
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent,
        ),
        leadingContent = {
            Icon(
                painter = getVector(material.category.icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(25.dp),
            )
        },
        headlineContent = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = getDimen(dimenValue = R.dimen.horizontalScreenPadding))
            ) {
                Text(material.name, style = MaterialTheme.typography.titleMedium)
            }

        },
        trailingContent = {
            Text(
                text = pointsLine,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    )
}