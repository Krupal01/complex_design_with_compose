package com.example.complexuicompose.activity.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import com.example.complexuicompose.activity.LocalFacebookCallbackManager
import com.example.complexuicompose.navigation.Screens
import com.example.complexuicompose.utils.AuthResult
import com.example.complexuicompose.viewmodel.MainViewModel
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.common.api.ApiException
import java.util.*

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    var text by remember { mutableStateOf<String?>(null) }
    val signInRequestCode = 1
    val callbackManager = LocalFacebookCallbackManager.current
    val context = LocalContext.current

    val authResultLauncher = rememberLauncherForActivityResult(contract = AuthResult()) { task ->
        try {
            val account = task?.getResult(ApiException::class.java)
            if (account == null) {
                text = "Google Sign In Failed"
            } else {
                navController.navigate(Screens.MainScreen.route)
            }
        } catch (e: ApiException) {
            text = e.localizedMessage
        }
    }

    DisposableEffect(Unit) {
        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.i("FB Sign In","onSuccess $loginResult")
                    navController.navigate(Screens.MainScreen.route)
                }

                override fun onCancel() {
                    Log.i("FB Sign In","onCancel")
                }

                override fun onError(exception: FacebookException) {
                    Log.i("FB Sign In","onError $exception")
                }
            }
        )
        onDispose {
            LoginManager.getInstance().unregisterCallback(callbackManager)
        }
    }

    Column() {
        AuthView(
            title = "Sign in with Google",
            errorText = text,
            onClick = {
                text = null
                authResultLauncher.launch(signInRequestCode)
            }
        )
        AuthView(
            title = "Sign in with Facebook",
            errorText = text,
            onClick = {
                text = null
                LoginManager.getInstance().setLoginBehavior(LoginBehavior.WEB_ONLY)
                    .logInWithReadPermissions(
                        context.findActivity()!!,
                        Arrays.asList("email", "user_friends"),
                    )
            }
        )
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AuthView(
    title: String,
    errorText: String?,
    onClick: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                isLoading = true
                onClick()
            }
        ){
            Text(text =title )
        }

        errorText?.let {
            isLoading = false

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = it
            )
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}