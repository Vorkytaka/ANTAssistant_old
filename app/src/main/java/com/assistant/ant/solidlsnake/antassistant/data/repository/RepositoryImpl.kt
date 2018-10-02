package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.net.Api
import com.assistant.ant.solidlsnake.antassistant.data.parser.Parser
import com.assistant.ant.solidlsnake.antassistant.data.pref.AuthPref
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository

object RepositoryImpl : IRepository {
    override suspend fun isLogged(): Boolean {
        return AuthPref.isLogged
    }

    override suspend fun auth(login: String, password: String): Boolean {
        val body = Api.info(login, password)
        val result = Parser.isLogged(body)

        if (result) {
            // todo: переделать на AccountManager
            AuthPref.login = login
            AuthPref.password = password
        }

        return result
    }

    override suspend fun getUserData(): UserData {
        val login = AuthPref.login
        val password = AuthPref.password

        val body = Api.info(login, password)

        TODO()
    }
}