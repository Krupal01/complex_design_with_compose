package com.example.complexuicompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.complexuicompose.repository.PreferenceUserRepository
import com.example.complexuicompose.repository.ProtoUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val protoUserRepository: ProtoUserRepository,
    private val preferenceUserRepository: PreferenceUserRepository
) : ViewModel() {

    // proto datastore
    var userFlow = protoUserRepository.getLoggedUser()

    fun updateValue( username : String , isLogged : Boolean) = viewModelScope.launch(Dispatchers.IO) {
        protoUserRepository.saveUserLogged(isLogged = isLogged , username = username)
    }

    //preference datastore
    var preferenceUserFlow = preferenceUserRepository.usernameFlow
    var preferenceIsLoginFlow = preferenceUserRepository.isLoginFlow

    fun updatePreferenceUser(username : String , isLogin:Boolean) = viewModelScope.launch(Dispatchers.IO) {
        preferenceUserRepository.setUser(username,isLogin)
    }
}