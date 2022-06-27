package com.example.complexuicompose.activity.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*


class BatteryShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = batteryPath(size = size)
        )
    }
}

fun batteryPath(
    size :Size,
): Path {
    return Path().apply {
        reset()
        addRect(
            rect = Rect(
                left = size.width.times(0.2).toFloat(),
                right = size.width.times(0.8).toFloat(),
                top = size.height.times(0),
                bottom = size.height.times(0.2).toFloat()
            )
        )
        addRect(
            rect = Rect(
                left = size.width.times(0),
                right = size.width.times(1),
                top = size.height.times(0.1).toFloat(),
                bottom = size.height.times(1)
            )
        )
    }
}

@Composable
fun BatteryCompose(
    modifier: Modifier,
    percentage: Float = 0.8f,
    textStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.Black,
        fontSize = 10.sp
    ),
    backgroundColor : Color = Color.White,
    color: Color = Color.Green
){

    Box(
        modifier = modifier
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = BatteryShape()
                clip = true
            }
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center

    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
            onDraw = {
                drawRect(
                    color = color,
                    topLeft = Offset((size.width * 0.1f), size.height.times((0.7f.times(1-percentage))+0.2f)),
                    size = Size(size.width*0.8f , size.height*0.7f*percentage)
                )
            }
        )
        Text(
            text = "56%",
            textAlign = TextAlign.Center,
            modifier = Modifier.wrapContentSize(),
            style = textStyle

        )
    }
}