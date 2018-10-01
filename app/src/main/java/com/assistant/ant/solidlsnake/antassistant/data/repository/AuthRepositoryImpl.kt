package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.net.Api
import com.assistant.ant.solidlsnake.antassistant.data.pref.AuthPref
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IAuthRepository

object AuthRepositoryImpl : IAuthRepository {
    override suspend fun isLogged(): Boolean {
        return AuthPref.isLogged
    }

    override suspend fun auth(login: String, password: String): Boolean {
        return Api.auth(login, password)
    }
}