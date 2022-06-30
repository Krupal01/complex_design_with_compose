package com.example.complexuicompose.module

import android.content.Context
import com.example.complexuicompose.repository.ProtoUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Provides
    @Singleton
    fun getUserRepository(@ApplicationContext context: Context): ProtoUserRepository {
        return ProtoUserRepository(context)
    }

}