package com.dicoding.findanimal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.findanimal.data.Repository
import com.dicoding.findanimal.ui.screen.add.AddAnimalViewModel
import com.dicoding.findanimal.ui.screen.detail.DetailViewModel
import com.dicoding.findanimal.ui.screen.favorite.FavoriteViewModel
import com.dicoding.findanimal.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(AddAnimalViewModel::class.java)) {
            return AddAnimalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}