package com.example.complexuicompose.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.complexuicompose.activity.screen.PreferenceDatastoreCheck
import com.example.complexuicompose.activity.screen.ProtoDataStoreCheck
import com.example.complexuicompose.navigation.NavHostScreen
import com.example.complexuicompose.ui.theme.ComplexUIComposeTheme
import com.example.complexuicompose.viewmodel.MainViewModel
import com.facebook.CallbackManager
import dagger.hilt.android.AndroidEntryPoint

val LocalFacebookCallbackManager = staticCompositionLocalOf<CallbackManager> { error("no CallbackManager Provided") }

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val callbackManager = CallbackManager.Factory.create()
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ComplexUIComposeTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalFacebookCallbackManager provides callbackManager) {
                    NavHostScreen(navController = navController,viewModel = viewModel)
                }
                
//                ProtoDataStoreCheck(viewModel = viewModel)
                
//                PreferenceDatastoreCheck(viewModel = viewModel)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode = requestCode, resultCode = resultCode, data = data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}

