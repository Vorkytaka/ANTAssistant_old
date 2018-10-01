package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.pref.AuthPref
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IAuthRepository
import kotlinx.coroutines.delay

object AuthRepositoryImpl : IAuthRepository {
    override suspend fun isLogged(): Boolean {
        return AuthPref.isLogged
    }

    override suspend fun auth(login: String, password: String): Boolean {
        delay(1000)
        return false
    }
}