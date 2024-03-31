package com.dicoding.findanimal.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.findanimal.data.Animal
import com.dicoding.findanimal.data.Repository
import com.dicoding.findanimal.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Animal>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Animal>>
        get() = _uiState

    private val _isAnimalSaved = MutableStateFlow(false)
    val isAnimalSaved: StateFlow<Boolean> get() = _isAnimalSaved

    fun getAnimalById(animalId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getAnimalById(animalId))
        }
    }

    fun isFavorite(animalId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _isAnimalSaved.value = repository.isFavorite(animalId)
        }
    }

    fun saveFavoriteAnimal(favoriteAnimal: Animal) {
        _isAnimalSaved.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveFavoriteAnimal(favoriteAnimal)
        }
    }

    fun deleteFavoriteAnimal(favoriteAnimal: Animal): Job {
        _isAnimalSaved.value = false
        return viewModelScope.launch(Dispatchers.IO) {
            repository.delete(favoriteAnimal)
        }
    }
}