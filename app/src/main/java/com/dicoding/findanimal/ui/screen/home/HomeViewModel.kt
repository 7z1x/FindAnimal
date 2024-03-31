package com.dicoding.findanimal.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dicoding.findanimal.data.Animal
import com.dicoding.findanimal.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _filteredAnimals = MutableStateFlow(
        repository.getAnimals()
            .sortedBy { it.animalTitle }
    )
    val filteredAnimals: StateFlow<List<Animal>> get() = _filteredAnimals

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        _filteredAnimals.value = repository.searchAnimals(_query.value)
            .sortedBy { it.animalTitle }
    }
}