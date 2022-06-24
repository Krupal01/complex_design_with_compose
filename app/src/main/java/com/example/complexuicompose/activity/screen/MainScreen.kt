package com.example.complexuicompose.activity.screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.complexuicompose.activity.composable.SwipeItemLazyColumn

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController
) {
    
    val listm = remember {
        mutableStateListOf("sd","Sfd","sdfsd","sfsd")
    }
    SwipeItemLazyColumn(
        modifier = Modifier.fillMaxWidth().padding(all = 2.dp),
        items = listm,
        itemCompose = {
            Text(text = it.toString(), modifier = Modifier.fillMaxWidth())
        },
        directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
        swipeAction = {
            when(it.dismissDirection){
                DismissDirection.StartToEnd -> {
                    Log.i("action","start to end")
                }
                DismissDirection.EndToStart -> {
                    Log.i("action","end to start")
                }
                null -> {
                    Log.i("action","null")
                }

            }
        }
    )


}