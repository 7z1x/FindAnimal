package com.dicoding.findanimal.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.findanimal.R
import com.dicoding.findanimal.di.Injection
import com.dicoding.findanimal.ui.ViewModelFactory
import com.dicoding.findanimal.ui.components.AnimalList


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val filteredAnimals by homeViewModel.filteredAnimals.collectAsState()
    val query by homeViewModel.query
    Column {
        SearchBar(
            query = query,
            onQueryChange = homeViewModel::search,
        )
        AnimalList(animals = filteredAnimals, navigateToDetail = navigateToDetail)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String, onQueryChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    SearchBar(query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(stringResource(R.string.search_animal))
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)

    ) {}
}