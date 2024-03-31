@file:Suppress("KotlinConstantConditions")

package com.dicoding.findanimal

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.findanimal.ui.navigation.NavigationItem
import com.dicoding.findanimal.ui.navigation.Screen
import com.dicoding.findanimal.ui.screen.about.AboutScreen
import com.dicoding.findanimal.ui.screen.add.AddAnimalScreen
import com.dicoding.findanimal.ui.screen.detail.DetailScreen
import com.dicoding.findanimal.ui.screen.favorite.FavoriteScreen
import com.dicoding.findanimal.ui.screen.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindAnimal(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Favorite.route) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.secondary,
                    ),
                    title = {
                        when (currentRoute) {
                            Screen.Favorite.route -> Text(stringResource(id = R.string.favorite_screen))
                        }
                    },
                )
            }
        },
        floatingActionButton = {
            if (currentRoute == Screen.Home.route) {
                FloatingActionButton(onClick = {
                    navController.navigate(Screen.Add.route)
                }) {
                    Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_animal_button))
                }
            }
        },
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Favorite.route || currentRoute == Screen.About.route) {
                BottomBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen { animalId ->
                    navController.navigate(Screen.Detail.createRoute(animalId))
                }
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { animalId ->
                        navController.navigate(Screen.Detail.createRoute(animalId))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(Screen.Add.route) {
                AddAnimalScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navController = navController
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("animalId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("animalId") ?: -1L
                DetailScreen(
                    animalId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.Person,
                screen = Screen.About
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
