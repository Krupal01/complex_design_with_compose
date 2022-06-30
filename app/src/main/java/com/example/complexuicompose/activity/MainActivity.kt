package com.example.complexuicompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.complexuicompose.activity.screen.PreferenceDatastoreCheck
import com.example.complexuicompose.activity.screen.ProtoDataStoreCheck
import com.example.complexuicompose.navigation.NavHostScreen
import com.example.complexuicompose.ui.theme.ComplexUIComposeTheme
import com.example.complexuicompose.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ComplexUIComposeTheme {
                val navController = rememberNavController()
                NavHostScreen(navController = navController,viewModel = viewModel)
                
//                ProtoDataStoreCheck(viewModel = viewModel)
                
//                PreferenceDatastoreCheck(viewModel = viewModel)
            }
        }
    }
}

