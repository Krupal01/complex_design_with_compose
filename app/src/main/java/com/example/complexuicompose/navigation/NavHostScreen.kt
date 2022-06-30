package com.example.complexuicompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.complexuicompose.activity.screen.LoginScreen
import com.example.complexuicompose.activity.screen.MainScreen
import com.example.complexuicompose.activity.screen.SplashScreen
import com.example.complexuicompose.viewmodel.MainViewModel

@Composable
fun NavHostScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController = navController , startDestination = Screens.SplashScreen.route){

        composable(
            route = Screens.SplashScreen.route
        ){
            SplashScreen(navController = navController)
        }

        composable(
            route = Screens.LoginScreen.route
        ){
            LoginScreen(navController = navController,viewModel = viewModel)
        }

        composable(
            route = Screens.MainScreen.route
        ){
            MainScreen(navController = navController)
        }

    }
}