package com.example.greenifyme.ui.database.panel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.R
import com.example.greenifyme.data.DataObjectType
import com.example.greenifyme.data.GreenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DatabasePanelModel(
    private val mainRepository: GreenRepository,
    private val sampleRepository: GreenRepository
) : ViewModel() {

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    private val _state = MutableStateFlow(DBPanelState())
    val state: StateFlow<DBPanelState> = _state

    fun showDialog(action: DBPanelAction) {
        _state.update {
            it.copy(showDialog = true, selectedAction = action)
        }
    }

    fun onDismissDialog() {
        _state.update {
            it.copy(showDialog = false, selectedAction = null)
        }
    }

    fun onConfirmDialog(action: DBPanelAction?, context: Context) {
        onDismissDialog()
        when (action) {
            DBPanelAction.DELETE_MAIN -> onDeleteMainConfirmed(context)
            DBPanelAction.DELETE_SAMPLE -> onDeleteSampleConfirmed(context)
            DBPanelAction.RESET_SAMPLE -> onResetSample(context)
            DBPanelAction.RESET_MAIN -> onResetMain(context)
            null -> {}
        }
    }

    private fun onDeleteMainConfirmed(context: Context) {
        mainRepository.deleteAll(scope = viewModelScope)
        mainRepository.init(DataObjectType.MATERIAL, viewModelScope)
        showToast(context.getString(R.string.database_panel_main_db_deleted))
    }

    private fun onDeleteSampleConfirmed(context: Context) = viewModelScope.launch {
        sampleRepository.deleteAll(scope = viewModelScope)
        showToast(context.getString(R.string.database_panel_sample_db_deleted))
    }

    private fun onResetSample(context: Context) = viewModelScope.launch {
        try {
            withContext(Dispatchers.IO) {
                sampleRepository.deleteAll(scope = viewModelScope)
            }
            delay(500)
            withContext(Dispatchers.IO) {
                sampleRepository.init(DataObjectType.ACCOUNT, viewModelScope)
            }
            withContext(Dispatchers.IO) {
                sampleRepository.init(DataObjectType.MATERIAL, viewModelScope)
            }
            delay(500)
            withContext(Dispatchers.IO) {
                sampleRepository.init(DataObjectType.FORM, viewModelScope)
            }
            delay(500)
            withContext(Dispatchers.IO) {
                sampleRepository.init(DataObjectType.TRACK, viewModelScope)
            }
            showToast(context.getString(R.string.database_panel_sample_db_reset))
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Failed")
        }
    }


    private fun onResetMain(context: Context) = viewModelScope.launch {
        mainRepository.deleteAll(scope = viewModelScope)
        delay(500)
        mainRepository.init(DataObjectType.MATERIAL, viewModelScope)
        showToast(context.getString(R.string.database_panel_main_db_reset))
    }

    private fun showToast(text: String) {
        _toastMessage.value = text
    }

    fun clearToastMessage() {
        viewModelScope.launch {
            _toastMessage.emit(null)
        }
    }
}

data class DBPanelState(
    val showDialog: Boolean = false,
    val selectedAction: DBPanelAction? = null
)

enum class DBPanelAction {
    DELETE_MAIN, DELETE_SAMPLE, RESET_SAMPLE, RESET_MAIN
}