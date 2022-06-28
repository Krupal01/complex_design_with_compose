package com.example.complexuicompose.activity.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

//Constants for route
const val SCREEN1 = "SCREEN1"
const val SCREEN2 = "SCREEN2"
const val SCREEN3 = "SCREEN3"

@Composable
fun BadgesBottomNavBar(
    bottomNavItems : List<BottomNavModel>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Gray,
    contentColor: Color = Color.Green,
    elevation : Dp = 2.dp,
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavItems.forEach {
            BottomNavigationItem(
                selected = currentRoute==it.route,
                onClick = {
                    navController.backQueue.clear()
                    navController.navigate(it.route)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (it.badges>0){
                                Badge(){
                                    Text(text = it.badges.toString())
                                }
                            }
                        }
                    ) {
                        Icon(imageVector = it.icon, contentDescription = it.name)
                    }
                },
                label = {
                    Text(text = it.name)
                },
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
fun NavHostForBottomNavBar(
    padding: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = SCREEN1,
        modifier = Modifier
            .padding(paddingValues = padding)
            .fillMaxSize()
    ){
        composable(SCREEN1){
            Screen1()
        }
        composable(SCREEN2){
            Screen2()
        }
        composable(SCREEN3){
            Screen3()
        }
    }

}



//data class
data class BottomNavModel(
    val name : String,
    val icon : ImageVector,
    val route : String,
    val badges : Long
)

// Demo Screens
@Composable
fun Screen1() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Screen 1", color = Color.Black)
    }
}

@Composable
fun Screen2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Screen 2", color = Color.Black)
    }
}

@Composable
fun Screen3() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Screen 3", color = Color.White)
    }
}