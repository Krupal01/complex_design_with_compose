package com.example.complexuicompose.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.complexuicompose.User
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<User> {

    override suspend fun readFrom(input: InputStream): User {
        try {
            return User.parseFrom(input)
        }catch (e : InvalidProtocolBufferException){
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        t.writeTo(output)
    }

    override val defaultValue: User
        get()  = User.getDefaultInstance()
}