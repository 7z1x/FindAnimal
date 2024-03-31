package com.dicoding.findanimal.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.findanimal.R
import com.dicoding.findanimal.di.Injection
import com.dicoding.findanimal.ui.UiState
import com.dicoding.findanimal.ui.ViewModelFactory
import com.dicoding.findanimal.ui.components.CustomTopAppBar
import com.dicoding.findanimal.ui.components.HeightSpacer
import com.dicoding.findanimal.ui.theme.Typography

@Composable
fun DetailScreen(
    animalId: Long,
    detailViewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current)
        )
    ),
    navigateBack: () -> Unit
) {
    val isAnimalSaved by detailViewModel.isAnimalSaved.collectAsState()

    detailViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                detailViewModel.getAnimalById(animalId)
                detailViewModel.isFavorite(animalId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.animalCoverURL,
                    data.animalTitle,
                    data.scientificName,
                    data.origin,
                    data.category,
                    data.description,
                    isAnimalSaved,
                    setFavorite = {
                        if (!isAnimalSaved) {
                            detailViewModel.saveFavoriteAnimal(data)
                        } else {
                            detailViewModel.deleteFavoriteAnimal(data)
                        }

                    },
                    onBackClick = navigateBack
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    animalCoverURL: String,
    animalTitle: String,
    scientificName: String,
    origin: String,
    category: String,
    description: String,
    isAnimalSaved: Boolean,
    setFavorite: () -> Unit,
    onBackClick: () -> Unit,
) {

    Column {
        CustomTopAppBar(screenName = R.string.detail_screen, onBackClick)
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = setFavorite,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                        ,painter = painterResource(
                            id = if (isAnimalSaved) R.drawable.ic_baseline_favorite_24
                            else R.drawable.ic_baseline_favorite_border_24
                        ),
                        tint = if (isAnimalSaved) Color.Red else Color.Gray,
                        contentDescription = stringResource(R.string.favorite_button),
                    )
                }
                Text(
                    text = stringResource(R.string.animal_data, animalTitle),
                    modifier = Modifier.align(Alignment.Center),
                    style = Typography.titleLarge
                )
            }
            HeightSpacer(16.dp)
            AsyncImage(
                model = animalCoverURL,
                contentDescription = animalTitle,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally)
            )
            HeightSpacer(16.dp)
            Text(
                text = stringResource(R.string.animal_title, animalTitle),
                style = Typography.bodyLarge
            )
            HeightSpacer(8.dp)
            Text(
                text = stringResource(R.string.scientific_name, scientificName),
                style = Typography.bodyLarge
            )
            HeightSpacer(8.dp)
            Text(
                text = stringResource(R.string.origin, origin),
                style = Typography.bodyLarge
            )
            HeightSpacer(8.dp)
            Text(text = stringResource(R.string.category, category), style = Typography.bodyLarge)
            HeightSpacer(16.dp)
            Text(text = stringResource(R.string.Description), fontWeight = FontWeight.Bold, style = Typography.bodyLarge)
            HeightSpacer(8.dp)
            Text(text = stringResource(R.string.description, description), textAlign = TextAlign.Justify, style = Typography.bodyLarge)
        }
    }
}

@Preview (showBackground = true)
@Composable
fun DetailPreview() {
    DetailContent(
        animalCoverURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Lion_waiting_in_Namibia.jpg/800px-Lion_waiting_in_Namibia.jpg",
        animalTitle = "Singa",
        scientificName = "Panthero Leo",
        origin = "Afrika bagian timur dan selatan",
        category = "Mammalia",
        description = "spesies hewan dari keluarga felidae atau keluarga kucing. Singa berada di benua Afrika dan sebagian di wilayah India. Singa merupakan hewan yang hidup berkelompok. Biasanya terdiri dari seekor jantan & banyak betina. Kelompok ini menjaga daerah kekuasaannya. Umur singa antara 10 sampai 15 tahun di alam bebas, tetapi dalam penangkaran memungkinkan lebih dari 20 tahun. Singa yang lebih muda akan merebut kepemimpinan dari singa yang lebih tua. Kebanyakan singa yang lebih muda akan memakan anak singa dari pemimpin sebelumnya." +
                "Singa betina jauh lebih aktif dalam berburu, sedangkan singa jantan lebih santai bersikap menunggu & meminta jatah dari hasil buruan para betinanya. Singa jantan dipercaya lebih unggul dan perkasa dibandingkan dengan kucing besar lainnya, tetapi kelemahan singa ialah tidak bisa memanjat pohon sebagus kucing-kucing besar lainnya. Singa jantan ditumbuhi bulu tebal di sekitar tengkuknya, hal ini lebih menguntungkan untuk melindungi tengkuknya, terutama dalam perkelahian bebas antara kucing besar yang cenderung menerkam tengkuk untuk melumpuhkan musuhnya.",
        isAnimalSaved = true,
        setFavorite = { /*TODO*/ }
    ) {

    }
}