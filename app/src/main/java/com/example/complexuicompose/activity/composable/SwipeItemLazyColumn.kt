package com.example.complexuicompose.activity.composable

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeItemLazyColumn(
    modifier : Modifier = Modifier,
    items: List<Any>,
    itemCompose: @Composable (Any) -> Unit,
    directions: Set<DismissDirection> = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
    defaultColor: Color = Color.White,
    endToStartColor: Color = Color.Red,
    startToEndColor: Color = Color.Green,
    startToEndIcon: ImageVector = Icons.Default.Delete,
    endToStartIcon : ImageVector = Icons.Default.Edit,
    swipeAction : (DismissState) ->Unit

){
    LazyColumn {
        items(items){item ->
            val dismissState = rememberDismissState()

            LaunchedEffect(key1 = dismissState.dismissDirection){
                swipeAction(dismissState)
            }
            
            SwipeToDismiss(
                state = dismissState,
                modifier = modifier,
                directions = directions,
                dismissThresholds = {FractionalThreshold(0.2f)},
                background = {

                    val dismissDirection = dismissState.dismissDirection ?: return@SwipeToDismiss

                    val color by animateColorAsState(
                        targetValue = when (dismissState.targetValue) {
                            DismissValue.Default -> defaultColor
                            DismissValue.DismissedToEnd -> endToStartColor
                            DismissValue.DismissedToStart -> startToEndColor
                        },
                        animationSpec = tween(
                            durationMillis = 1000
                        )
                    )
                    val alignment = Alignment.CenterEnd
                    val icon = when (dismissDirection){
                        DismissDirection.StartToEnd -> startToEndIcon
                        DismissDirection.EndToStart -> endToStartIcon
                    }

                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = Dp(20f)),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Delete Icon",
                            modifier = Modifier.scale(scale)
                        )
                    }
                },
                dismissContent = {
                    itemCompose(item)
                }
            )
        }
    }
    
}

