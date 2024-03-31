package com.dicoding.findanimal.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.findanimal.data.Animal
import com.dicoding.findanimal.data.Repository
import com.dicoding.findanimal.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: Repository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Animal>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Animal>>>
        get() = _uiState

    fun getFavoriteAnimals() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFavoriteAnimal()
                .sortedBy { it.animalTitle })
        }
    }
}