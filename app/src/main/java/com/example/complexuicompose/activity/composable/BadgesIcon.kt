package com.example.complexuicompose.activity.composable

import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BadgesIcon(
    badges : Long= 0,
    icon : ImageVector,
    contentDescription : String?
) {
    BadgedBox(
        badge = {
            if (badges>0){
                Badge(){
                    Text(text = badges.toString())
                }
            }
        }
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}