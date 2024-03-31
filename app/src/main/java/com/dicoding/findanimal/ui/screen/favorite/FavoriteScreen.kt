package com.dicoding.findanimal.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.findanimal.di.Injection
import com.dicoding.findanimal.ui.UiState
import com.dicoding.findanimal.ui.ViewModelFactory
import com.dicoding.findanimal.ui.components.AnimalList

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (Long) -> Unit,
) {
    favoriteViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                favoriteViewModel.getFavoriteAnimals()
            }

            is UiState.Success -> {
                val favoriteAnimals = uiState.data
                AnimalList(animals = favoriteAnimals, navigateToDetail = navigateToDetail)
            }

            is UiState.Error -> {

            }
        }
    }
}