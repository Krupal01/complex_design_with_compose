package com.example.complexuicompose.activity.composable

import android.graphics.Point
import android.graphics.PointF
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LineChart(
    modifier: Modifier,
    listXValues: List<Float> ,
    listYValues: List<Float>
){

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(all = 20.dp)
    ){

        if (listYValues.size == listXValues.size){

            val distance = size.width / (listYValues.maxOrNull() ?:0f)
            val maxYValue = listYValues.maxOrNull() ?: 0f
            val points = mutableListOf<PointF>()

            listYValues.forEachIndexed { index, data ->
                if (listYValues.size >= index + 1) {
                    val y0 = (maxYValue - data) * (size.height.div( maxYValue))
                    val x0 = distance*listXValues[index]
                    points.add(PointF(x0, y0))
                }
            }

            for (i in 0 until points.size - 1) {
                drawLine(
                    start = Offset(points[i].x, points[i].y),
                    end = Offset(points[i + 1].x, points[i + 1].y),
                    color = Color(0xFFFF0000),
                    strokeWidth = 2f
                )

            }
        }
    }
}

@Preview
@Composable
fun PreviewChart(){
    LineChart(
        modifier = Modifier.size(width = 300.dp , height = 200.dp),
        listXValues = arrayListOf(0.0f,0.1f,0.3f,0.5f,0.6f,0.7f),
        listYValues =  arrayListOf(0.2f,0.3f,0.15f,0.4f,0.5f,0.7f),
    )
}

@Composable
fun BarChart(
    modifier: Modifier,
    points : List<Point>
) {


    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {

                    }
                )
            }
    ) {
        drawLine(
            start = Offset(10f, size.height),
            end = Offset(10f, 0f),
            color = Color.Black,
            strokeWidth = 3f
        )
        drawLine(
            start = Offset(0f,size.height - 10f),
            end = Offset(size.width, size.height -10f),
            color = Color.Black,
            strokeWidth = 3f
        )

        start = true

        for (p in points) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(p.x + 30f, size.height - (size.height - p.y) * heightPre),
                size = Size(55f, (size.height - p.y) * heightPre)
            )
        }
    }
}

@Preview
@Composable
fun PreviewBarChart(){
    BarChart(
        modifier = Modifier.size(width = 300.dp, height = 200.dp),
        points = listOf(
            Point(10, 10),
            Point(90, 100),
            Point(170, 30),
            Point(250, 200),
        )
    )
}