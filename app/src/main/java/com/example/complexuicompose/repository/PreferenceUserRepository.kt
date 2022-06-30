package com.example.complexuicompose.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val USERNAME_KEY = "USERNAME_KEY"
const val IS_LOGIN_KEY = "IS_LOGIN_KEY"
const val PREFERENCE_DATASTORE_NAME = "preference user datastore"

class PreferenceUserRepository(private val context: Context) {

    suspend fun setUser(username : String, isLogin : Boolean){
        context.preferenceUserDatastore.edit { preference ->
            preference[stringPreferencesKey(USERNAME_KEY)] = username
            preference[booleanPreferencesKey(IS_LOGIN_KEY)] = isLogin
        }
    }

    val isLoginFlow : Flow<Boolean>
        get()= context.preferenceUserDatastore.data.map {
                it-> (it[booleanPreferencesKey(IS_LOGIN_KEY)] ?: false)
        }

    val usernameFlow : Flow<String>
        get()= context.preferenceUserDatastore.data.map {
                it-> (it[stringPreferencesKey(USERNAME_KEY)] ?: " ")
        }

    companion object{
        private val Context.preferenceUserDatastore by preferencesDataStore(name = PREFERENCE_DATASTORE_NAME)
    }

}