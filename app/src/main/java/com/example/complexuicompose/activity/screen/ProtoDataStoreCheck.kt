package com.example.complexuicompose.activity.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.complexuicompose.User
import com.example.complexuicompose.viewmodel.MainViewModel

@Composable
fun ProtoDataStoreCheck(
    viewModel: MainViewModel
) {
    val user = viewModel.userFlow.collectAsState(initial = User.getDefaultInstance())
    val userNumber = remember {
        mutableStateOf(0)
    }
    Column() {
        Text(text = user.value.userName)
        Text(text = user.value.isLoggedIn.toString())
        Button(onClick = {
            viewModel.updateValue(
                username = "user${userNumber.value}",
                isLogged = (userNumber.value%2)==0
                )
            userNumber.value += 1
        }) {
            Text(text = "Add User")
        }
    }
}