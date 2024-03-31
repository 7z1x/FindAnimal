package com.dicoding.findanimal.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.findanimal.R
import com.dicoding.findanimal.ui.theme.Typography

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.foto_profile),
                contentDescription = stringResource(R.string.profile),
                modifier = Modifier
                    .size(320.dp)
                    .clip(CircleShape)
            )

            Text(
                text = stringResource(id = R.string.nama),
                style = Typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.email),
                style = Typography.titleMedium,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Composable
@Preview
fun AboutScreenPreview() {
    AboutScreen()
}