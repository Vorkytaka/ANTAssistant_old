package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.account.AccountManagerHolder
import com.assistant.ant.solidlsnake.antassistant.data.model.mapper.NetUserDataMapper
import com.assistant.ant.solidlsnake.antassistant.data.net.Api
import com.assistant.ant.solidlsnake.antassistant.data.parser.Parser
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository

object RepositoryImpl : IRepository {
    override suspend fun isLogged(): Boolean {
        if (!AccountManagerHolder.hasAccount()) return false

        val login = AccountManagerHolder.getAccount().name
        val password = AccountManagerHolder.getPassword()

        return auth(login, password)
    }

    override suspend fun auth(login: String, password: String): Boolean {
        val body = Api.info(login, password)
        val result = Parser.isLogged(body)

        if (result) {
            AccountManagerHolder.setAccount(login, password)
        }

        return result
    }

    override suspend fun getUserData(): UserData {
        val login = AccountManagerHolder.getAccount().name
        val password = AccountManagerHolder.getPassword()

        val body = Api.info(login, password)
        val netData = Parser.userData(body)

        return NetUserDataMapper().map(netData)
    }
}