package com.example.complexuicompose.activity.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.complexuicompose.R
import com.example.complexuicompose.activity.composable.BatteryCompose
import com.example.complexuicompose.activity.composable.CollapsableToolbar
import com.example.complexuicompose.activity.composable.MotionLayoutCompose
import com.example.complexuicompose.activity.composable.SwipeItemLazyColumn

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController
) {


    BatteryCompose(
        modifier = Modifier.size(width = 100.dp , height = 200.dp),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal
        ),
        backgroundColor = Color.Black,
        color = Color.Green,
        percentage = 0.8f
    )


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