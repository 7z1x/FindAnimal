package com.dicoding.findanimal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dicoding.findanimal.ui.theme.FindAnimalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindAnimalTheme {
                FindAnimal()
            }
        }
    }
}
