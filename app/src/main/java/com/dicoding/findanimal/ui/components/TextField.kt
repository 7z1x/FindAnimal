package com.dicoding.findanimal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextField(
    label: String,
    hint: String,
    input: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            value = input,
            label = { Text(hint) },
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}