package com.dicoding.findanimal.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.dicoding.findanimal.R
import com.dicoding.findanimal.data.Animal

@Composable
fun AnimalList(
    animals: List<Animal>,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()
    ) {
        if (animals.isEmpty()) {
            Box(
                modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.list_empty_message))
            }
        } else {
            LazyColumn(
                modifier.testTag("com.dicoding.findanimal.ui.components.AnimalList")
            ) {
                items(items = animals, key = {it.id} ) { animal ->
                    Animal_Item(photoUrl = animal.animalCoverURL,
                        animalTitle = animal.animalTitle,
                        description = animal.description,
                        modifier = Modifier.clickable {
                            navigateToDetail(animal.id)
                        })
                }
            }
        }
    }
}