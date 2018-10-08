package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.account.AccountManagerHolder
import com.assistant.ant.solidlsnake.antassistant.data.remote.IRemoteService
import com.assistant.ant.solidlsnake.antassistant.data.remote.response.mapper.UserDataResponseMapper
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class RepositoryImpl(
        private val remoteService: IRemoteService
) : IRepository {
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
        val result = remoteService.auth(login, password)

        if (result) {
            AccountManagerHolder.setAccount(login, password)
        }

        send(result)
    }

    override suspend fun getUserData(): ReceiveChannel<UserData?> = GlobalScope.produce {
        val login = AccountManagerHolder.getAccount().name
        val password = AccountManagerHolder.getPassword()

        val netData = remoteService.getUserData(login, password)

        send(UserDataResponseMapper().map(netData!!))
    }
}