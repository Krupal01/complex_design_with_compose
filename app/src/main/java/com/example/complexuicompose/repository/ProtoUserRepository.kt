package com.example.complexuicompose.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.complexuicompose.User
import com.example.complexuicompose.serializer.UserSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

const val USER_DATA_STORE_FILE_NAME = "user_datastore.pb"
class ProtoUserRepository(private val context: Context) {

    suspend fun saveUserLogged(isLogged : Boolean, username : String){
        context.protoDataStore.updateData {user ->
            user.toBuilder()
                .setIsLoggedIn(isLogged)
                .setUserName(username)
                .build()
        }
    }

    fun getLoggedUser() : Flow<User>{
        return context.protoDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(User.getDefaultInstance())
                } else {
                    throw exception
                }
            }
    }

    companion object{
        val Context.protoDataStore: DataStore<User> by dataStore(
            fileName = USER_DATA_STORE_FILE_NAME,
            serializer = UserSerializer
        )
    }
}