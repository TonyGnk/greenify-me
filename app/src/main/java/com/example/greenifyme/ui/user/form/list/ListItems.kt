package com.example.greenifyme.ui.user.form.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greenifyme.data.Track
import com.example.greenifyme.ui.user.form.UserFormModel
import com.example.greenifyme.ui.user.form.UserFormState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun UserFormList(model: UserFormModel, state: UserFormState, modifier: Modifier = Modifier) {
    val tracks = model.tracksOfForm.collectAsState()
    LazyColumn(
        contentPadding = PaddingValues(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
//        item {
//            Text(model.form.value.formId.toString())
//        }
        items(items = tracks.value) { track ->
            TrackItem(track = track)
        }
    }
}

@Composable
private fun TrackItem(track: Track) {
    ListItem(headlineContent = {
        Text(text = track.trackId.toString() + " " + track.formId.toString())
    })
}