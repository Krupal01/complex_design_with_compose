package com.example.complexuicompose.navigation

sealed class Screens(val route : String) {
    object SplashScreen : Screens(route = "Splash Screen")
    object MainScreen : Screens(route = "Main Screen")
}