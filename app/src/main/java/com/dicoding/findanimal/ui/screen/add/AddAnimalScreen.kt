package com.dicoding.findanimal.ui.screen.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dicoding.findanimal.R
import com.dicoding.findanimal.di.Injection
import com.dicoding.findanimal.ui.ViewModelFactory
import com.dicoding.findanimal.ui.components.CustomTopAppBar
import com.dicoding.findanimal.ui.components.TextField
import com.dicoding.findanimal.ui.navigation.Screen
import com.dicoding.findanimal.ui.theme.Typography

@Composable
fun AddAnimalScreen(
    addBookViewModel: AddAnimalViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current)
        )
    ),
    navigateBack: () -> Unit,
    navController: NavController,
) {
    var animalCoverURL by remember { mutableStateOf("") }
    var animalTitle by remember { mutableStateOf("") }
    var scientificName by remember { mutableStateOf("") }
    var origin by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column {
        CustomTopAppBar(screenName = R.string.add_screen, navigateBack)
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.add_new_animal),
                style = Typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            TextField(
                label = stringResource(R.string.animal_cover_url_label),
                hint = stringResource(R.string.enter_url_animal_cover),
                input = animalCoverURL,
                modifier = Modifier,
            ) { newInput ->
                animalCoverURL = newInput
            }
            TextField(
                label = stringResource(R.string.animal_title_label),
                hint = stringResource(R.string.enter_animal_title),
                input = animalTitle,
                modifier = Modifier,
            ) { newInput ->
                animalTitle = newInput
            }
            TextField(
                label = stringResource(R.string.scientific_name_label),
                hint = stringResource(R.string.enter_scientific_name),
                input = scientificName,
                modifier = Modifier,
            ) { newInput ->
                scientificName = newInput
            }
            TextField(
                label = stringResource(R.string.origin_label),
                hint = stringResource(R.string.enter_origin),
                input = origin,
                modifier = Modifier,
            ) { newInput ->
                origin = newInput
            }
            TextField(
                label = stringResource(R.string.category_label),
                hint = stringResource(R.string.enter_category),
                input = category,
                modifier = Modifier,
            ) { newInput ->
                category = newInput
            }

            TextField(
                label = stringResource(R.string.description_label),
                hint = stringResource(R.string.enter_description),
                input = description,
                modifier = Modifier,
            ) { newInput ->
                description = newInput
            }
            Button(
                onClick = {
                    addBookViewModel.saveAnimal(
                        animalCoverURL,
                        animalTitle,
                        scientificName,
                        origin,
                        category,
                        description
                    )
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.add_animal))
            }
        }
    }
}