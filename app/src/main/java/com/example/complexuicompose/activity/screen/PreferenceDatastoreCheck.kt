package com.example.complexuicompose.activity.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.complexuicompose.viewmodel.MainViewModel

@Composable
fun PreferenceDatastoreCheck(
    viewModel: MainViewModel
) {

    val username = viewModel.preferenceUserFlow.collectAsState(initial = "")
    val isLogin = viewModel.preferenceIsLoginFlow.collectAsState(initial = false)
    val userNumber = remember {
        mutableStateOf(0)
    }

    Column() {
        Text(text = username.value)
        Text(text = isLogin.value.toString())
        Button(onClick = {
            viewModel.updatePreferenceUser(
                username = "user${userNumber.value}",
                isLogin = userNumber.value%2 == 0
            )
            userNumber.value +=1
        }) {
            Text(text = "Add Preference User")
        }
    }
}