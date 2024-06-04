package com.example.greenifyme.ui.landing_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.GreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LandingPageViewModel(
    private val sampleRepository: GreenRepository,
    private val normalRepository: GreenRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LandingPageState())
    val uiState: StateFlow<LandingPageState> = _uiState

    fun initialize() {
        initializeNormal()
        initializeSample()
    }

    private fun initializeNormal() = viewModelScope.launch {
        normalRepository.getMaterials().collect {
            return@collect
        }
    }

    private fun initializeSample() = viewModelScope.launch {
        sampleRepository.getMaterials().collect {
            return@collect
        }
    }

    fun onInfoClicked() {
        _uiState.update {
            it.copy(showDialog = true)
        }
    }

    fun dialogDismiss() {
        _uiState.update {
            it.copy(showDialog = false)
        }
    }

}

data class LandingPageState(
    val showDialog: Boolean = false
)
//
//val intentNormalAdmin = Intent(context, AdminHomeActivity::class.java).apply {
//    putExtra("UseSampleData", false)
//}
//val intentNormalLogin = Intent(context, LoginNavigationActivity::class.java)