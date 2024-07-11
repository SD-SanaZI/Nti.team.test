package com.example.ntiteamtest.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screens(val route: String) {
    data object Greeting: Screens("greeting_screen")
    data object Config: Screens("configs_screen")
}

@Composable
fun NavGraph (navController: NavHostController, viewModel: MainViewModel){
    NavHost(
        navController = navController,
        startDestination = Screens.Greeting.route)
    {
        composable(route = Screens.Greeting.route){
            Greeting(viewModel,navController)
        }
        composable(route = Screens.Config.route){
            Config(viewModel,navController)
        }
    }
}