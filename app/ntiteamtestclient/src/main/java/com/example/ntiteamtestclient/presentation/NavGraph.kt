package com.example.ntiteamtestclient.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screens(val route: String) {
    data object Main: Screens("main_screen")
    data object Config: Screens("configs_screen")
    data object TouchList: Screens("touch_list_screen")
}

@Composable
fun NavGraph (navController: NavHostController, viewModel: MainViewModel){
    NavHost(
        navController = navController,
        startDestination = Screens.Main.route)
    {
        composable(route = Screens.Main.route){
            Main(viewModel,navController)
        }
        composable(route = Screens.Config.route){
            Config(viewModel,navController)
        }
        composable(route = Screens.TouchList.route){
            TouchList(viewModel,navController)
        }
    }
}