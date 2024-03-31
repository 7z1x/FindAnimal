package com.dicoding.findanimal.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object Add : Screen("home/add")
    object Detail : Screen("home/{animalId}") {
        fun createRoute(animalId: Long) = "home/$animalId"
    }
}