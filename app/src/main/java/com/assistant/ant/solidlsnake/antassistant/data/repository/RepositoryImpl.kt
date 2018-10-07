package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.account.AccountManagerHolder
import com.assistant.ant.solidlsnake.antassistant.data.model.mapper.NetUserDataMapper
import com.assistant.ant.solidlsnake.antassistant.data.net.Api
import com.assistant.ant.solidlsnake.antassistant.data.parser.Parser
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

object RepositoryImpl : IRepository {
    override suspend fun isLogged(): ReceiveChannel<Boolean> = GlobalScope.produce {
        if (!AccountManagerHolder.hasAccount()) {
            send(false)
            this.close()
        }

        val login = AccountManagerHolder.getAccount().name
        val password = AccountManagerHolder.getPassword()

        send(auth(login, password).receive())
    }

    override suspend fun auth(login: String, password: String): ReceiveChannel<Boolean> = GlobalScope.produce {
        val body = Api.info(login, password).await().string()
        val result = Parser.isLogged(body)

        if (result) {
            AccountManagerHolder.setAccount(login, password)
        }

        send(result)
    }

    override suspend fun getUserData(): ReceiveChannel<UserData> = GlobalScope.produce {
        val login = AccountManagerHolder.getAccount().name
        val password = AccountManagerHolder.getPassword()

        val body = Api.info(login, password).await().string()
        val netData = Parser.userData(body)

        send(NetUserDataMapper().map(netData))
    }
}