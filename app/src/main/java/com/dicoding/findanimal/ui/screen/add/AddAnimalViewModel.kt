package com.dicoding.findanimal.ui.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.findanimal.data.Repository
import kotlinx.coroutines.launch

class AddAnimalViewModel(private val repository: Repository) : ViewModel() {

    fun saveAnimal(
        animalCoverURL: String,
        animalTitle: String,
        scientificName: String,
        origin: String,
        category: String,
        description: String
    ) {
        viewModelScope.launch {
            repository.saveAnimal(
                animalCoverURL,
                animalTitle,
                scientificName,
                origin,
                category,
                description
            )
        }
    }
}