package com.example.complexuicompose.activity.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.complexuicompose.R
import com.example.complexuicompose.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true ){
        scale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.popBackStack()
        navController.navigate(Screens.MainScreen.route)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground) ,
            contentDescription = null ,
            colorFilter = ColorFilter.tint(color = Color.Green),
            modifier = Modifier.scale(scale = scale.value)
        )
    }

}

@Preview
@Composable
fun PreviewSplashScreen(
    navController : NavController = rememberNavController()
){
    SplashScreen(navController = navController)
}