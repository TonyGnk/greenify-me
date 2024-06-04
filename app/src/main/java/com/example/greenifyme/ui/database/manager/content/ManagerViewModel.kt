package com.example.greenifyme.ui.database.manager.content

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenifyme.data.Account
import com.example.greenifyme.data.DataObject
import com.example.greenifyme.data.DataObjectType
import com.example.greenifyme.data.Form
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.Material
import com.example.greenifyme.data.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Manages the content displayed in the UI, including accounts, forms, tracks, and materials.
 *
 * @param repository The repository to interact with the data source.
 */
class ManagerViewModel(private val repository: GreenRepository) : ViewModel() {

    private val _state = MutableStateFlow(ContentState())
    val state: StateFlow<ContentState> = _state

    init {
        loadItemsByType()
    }

    private fun loadItemsByType() {
        viewModelScope.launch {
            repository.getAccounts().collect { list ->
                _state.update {
                    it.copy(accounts = list)
                }
            }
        }
        viewModelScope.launch {
            repository.getForms().collect { list ->
                _state.update {
                    it.copy(forms = list)
                }
            }
        }
        viewModelScope.launch {
            repository.getTracks().collect { list ->
                _state.update {
                    it.copy(tracks = list)
                }
            }
        }
        viewModelScope.launch {
            repository.getMaterials().collect { list ->
                _state.update {
                    it.copy(materials = list)
                }
            }
        }
    }

    private fun loadItemsByType(type: DataObjectType) {
        viewModelScope.launch {
            when (type) {
                DataObjectType.ACCOUNT -> repository.getAccounts().collect { list ->
                    _state.update { it.copy(accounts = list) }
                }

                DataObjectType.FORM -> repository.getForms().collect { list ->
                    _state.update { it.copy(forms = list) }
                }

                DataObjectType.TRACK -> repository.getTracks().collect { list ->
                    _state.update { it.copy(tracks = list) }
                }

                DataObjectType.MATERIAL -> repository.getMaterials().collect { list ->
                    _state.update { it.copy(materials = list) }
                }
            }
        }
    }


    fun deleteItemFromDatabase(item: DataObject) {
        repository.delete(item, viewModelScope)
        val type = when (item) {
            is Account -> DataObjectType.ACCOUNT
            is Form -> DataObjectType.FORM
            is Track -> DataObjectType.TRACK
            is Material -> DataObjectType.MATERIAL
        }
        loadItemsByType(type)
    }

    fun searchItems(
        query: String, type: DataObjectType, context: Context
    ) {
        _state.update {
            it.copy(
                searchQuery = query,
                isSearching = query.isNotEmpty()
            )
        }
        if (query.toIntOrNull() != null) {
            loadItemsWithFilterById(type, query.toInt())
        } else {
            loadItemsWithFilterByName(type, query, context)
        }
    }

    fun closeSearchAndReload(type: DataObjectType) {
        _state.update {
            it.copy(
                searchQuery = "",
                isSearching = false
            )
        }
        loadItemsByType(type)
    }

    private fun loadItemsWithFilterById(type: DataObjectType, query: Int) {
        viewModelScope.launch {
            when (type) {
                DataObjectType.ACCOUNT -> repository.getAccounts().collect { list ->
                    val filteredList = list.filter {
                        it.accountId == query
                    }
                    _state.update { it.copy(accounts = filteredList) }
                }

                DataObjectType.FORM -> repository.getForms().collect { list ->
                    val filteredList = list.filter {
                        it.formId == query || it.accountId == query
                    }
                    _state.update { it.copy(forms = filteredList) }
                }

                DataObjectType.TRACK -> repository.getTracks().collect { list ->
                    val filteredList = list.filter {
                        it.trackId == query || it.formId == query || it.materialId == query
                    }
                    _state.update { it.copy(tracks = filteredList) }
                }

                DataObjectType.MATERIAL -> repository.getMaterials().collect { list ->
                    val filteredList = list.filter {
                        it.materialId == query
                    }
                    _state.update { it.copy(materials = filteredList) }
                }
            }
        }
    }

    private fun loadItemsWithFilterByName(type: DataObjectType, query: String, context: Context) {
        viewModelScope.launch {
            when (type) {
                DataObjectType.ACCOUNT -> repository.getAccounts().collect { list ->
                    val filteredList = list.filter {
                        it.name.contains(query, ignoreCase = true)
                    }
                    _state.update { it.copy(accounts = filteredList) }
                }

                DataObjectType.FORM -> {}

                DataObjectType.TRACK -> {}

                DataObjectType.MATERIAL -> repository.getMaterials().collect { list ->
                    val filteredList = list.filter {
                        it.name.contains(
                            query,
                            ignoreCase = true
                        ) || context.getString(it.category.description)
                            .contains(query, ignoreCase = true)
                    }
                    _state.update { it.copy(materials = filteredList) }
                }
            }
        }
    }
}

/**
 * Represents the state of content displayed in the UI.
 *
 * @property searchQuery The current search query.
 * @property isSearching Indicates if a search is active.
 * @property accounts The list of accounts.
 * @property forms The list of forms.
 * @property tracks The list of tracks.
 * @property materials The list of materials.
 */
data class ContentState(
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val accounts: List<Account> = listOf(),
    val forms: List<Form> = listOf(),
    val tracks: List<Track> = listOf(),
    val materials: List<Material> = listOf(),
)