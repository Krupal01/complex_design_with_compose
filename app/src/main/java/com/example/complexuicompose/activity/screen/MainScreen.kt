package com.example.complexuicompose.activity.screen

import android.annotation.SuppressLint
import android.graphics.Point
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.complexuicompose.R
import com.example.complexuicompose.activity.composable.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController
) {

    val childNavController = rememberNavController()
    var mDisplayMenu by remember { mutableStateOf(false) }
    val badgeHome = remember { mutableStateOf(0L) }
    val badgeEdit = remember { mutableStateOf(10L) }
    val badgePerson = remember { mutableStateOf(0L) }
    val badgeFavorite = remember { mutableStateOf(2L) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Main", color = Color.White) } ,
                backgroundColor = Color(0xff0f9d58),
                actions = {

                    IconButton(onClick = {}){
                        BadgesIcon(
                            icon = Icons.Default.Favorite,
                            contentDescription ="" ,
                            badges = badgeFavorite.value
                        )
                    }

                    IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                        Icon(Icons.Default.MoreVert, "")
                    }
                    // Creating a dropdown menu
                    DropdownMenu(
                        expanded = mDisplayMenu,
                        onDismissRequest = { mDisplayMenu = false }
                    ) {
                         DropdownMenuItem(onClick = { badgeFavorite.value+=1 }) {
                             Text(text = "Add Badge in Favorite")
                         }
                        DropdownMenuItem(onClick = { badgeHome.value+=1}) {
                            Text(text = "Add Badge in Home")
                        }
                        DropdownMenuItem(onClick = { badgeEdit.value+=1 }) {
                            Text(text = "Add Badge in Edit")
                        }
                        DropdownMenuItem(onClick = {badgePerson.value+=1 }) {
                            Text(text = "Add Badge in Person")
                        }
                    }
                }
            )
        },
        bottomBar = {
            BadgesBottomNavBar(
                bottomNavItems = listOf(
                    BottomNavModel(
                        name = SCREEN1,
                        icon = Icons.Filled.Home,
                        route = SCREEN1,
                        badges = badgeHome.value
                    ),
                    BottomNavModel(
                        name = SCREEN2,
                        icon = Icons.Filled.Edit,
                        route = SCREEN2,
                        badges = badgeEdit.value
                    ),
                    BottomNavModel(
                        name = SCREEN3,
                        icon = Icons.Filled.Person,
                        route = SCREEN3,
                        badges = badgeFavorite.value
                    ),
                ),
                navController = childNavController
            )
        }
    ) {
        NavHostForBottomNavBar(padding =it , navController = childNavController)
    }


//    Column(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        LineChart(
//            modifier = Modifier.padding(all = 10.dp).fillMaxWidth().weight(1f).border(width = 1.dp, color = Color.Black),
//            listXValues = arrayListOf(0.0f,0.1f,0.3f,0.5f,0.6f,0.7f),
//            listYValues =  arrayListOf(0.2f,0.3f,0.15f,0.4f,0.5f,0.7f),
//        )
//        BarChart(
//            modifier = Modifier.padding(all = 10.dp).fillMaxWidth().weight(1f).border(width = 1.dp, color = Color.Black),
//            points = listOf(
//                Point(10, 10),
//                Point(90, 100),
//                Point(170, 30),
//                Point(250, 200),
//            )
//        )
//    }

//    BatteryCompose(
//        modifier = Modifier.size(width = 100.dp , height = 200.dp),
//        textStyle = TextStyle(
//            color = Color.Black,
//            fontSize = 10.sp,
//            fontWeight = FontWeight.Normal
//        ),
//        backgroundColor = Color.Black,
//        color = Color.Green,
//        percentage = 0.8f
//    )


//    CollapsableToolbar (
//        title = {
//            Text(text = "title", fontWeight = FontWeight.Black)
//        },
//        toolbarBody = {
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_background),
//                contentDescription = null ,
//                modifier = Modifier.fillMaxWidth(),
//                contentScale = ContentScale.FillBounds
//            )
//        },
//        scrollableBody = {
//            Text(text = "hello world")
//        }
//    )

//    MotionLayoutCompose()

//    val listm = remember {
//        mutableStateListOf("sd","Sfd","sdfsd","sfsd")
//    }
//    SwipeItemLazyColumn(
//        modifier = Modifier.fillMaxWidth().padding(all = 2.dp),
//        items = listm,
//        itemCompose = {
//            Text(text = it.toString(), modifier = Modifier.fillMaxWidth())
//        },
//        directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
//        swipeAction = {
//            when(it.dismissDirection){
//                DismissDirection.StartToEnd -> {
//                    Log.i("action","start to end")
//                }
//                DismissDirection.EndToStart -> {
//                    Log.i("action","end to start")
//                }
//                null -> {
//                    Log.i("action","null")
//                }
//
//            }
//        }
//    )


}