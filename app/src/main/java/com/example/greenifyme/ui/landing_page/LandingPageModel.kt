package com.example.greenifyme.ui.landing_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.GreenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the LandingPage.
 *
 * @param sampleRepository Repository for accessing sample data.
 * @param normalRepository Repository for accessing normal data.
 *
 * This ViewModel handles the state and business logic for the landing page.
 * It uses two repositories: one for sample data and one for normal data.
 */
class LandingPageViewModel(
    private val sampleRepository: GreenRepository,
    private val normalRepository: GreenRepository
) : ViewModel() {

    // Backing property for UI state, MutableStateFlow allows us to emit updates to the state.
    private val _uiState = MutableStateFlow(LandingPageState())

    // Publicly exposed UI state as a StateFlow, which is read-only to prevent unwanted modifications.
    val uiState: StateFlow<LandingPageState> = _uiState

    /**
     * Initializes the ViewModel by ensuring that the databases are loaded.
     *
     * This function is typically called at the first launch. It forces the databases
     * to initialize by invoking a method that interacts with them. This is necessary
     * to ensure that the databases are ready for use when needed.
     */
    fun initialize() {
        // At the first launch (normally) the databases initialize when is needed
        // We force call a random function to wake up the database and initialize it at the landing page
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