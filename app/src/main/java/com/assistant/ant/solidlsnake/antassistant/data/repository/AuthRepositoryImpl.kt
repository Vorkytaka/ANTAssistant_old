package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.pref.AuthPref
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IAuthRepository

object AuthRepositoryImpl : IAuthRepository {
    override suspend fun isLogged(): Boolean {
        return AuthPref.isLogged
    }
}