package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.local.ILocalService
import com.assistant.ant.solidlsnake.antassistant.data.mapper.UserDataModelMapper
import com.assistant.ant.solidlsnake.antassistant.data.mapper.UserDataResponseMapper
import com.assistant.ant.solidlsnake.antassistant.data.remote.IRemoteService
import com.assistant.ant.solidlsnake.antassistant.domain.entity.AuthData
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class RepositoryImpl(
        private val remoteService: IRemoteService,
        private val localService: ILocalService
) : IRepository {
    override suspend fun isLogged(): ReceiveChannel<Boolean> = GlobalScope.produce {
        if (!localService.hasAccount()) {
            send(false)
            this.close()
        }

        val authData = localService.getAuthData()

        send(auth(authData.login, authData.password).receive())
    }

    override suspend fun auth(login: String, password: String): ReceiveChannel<Boolean> = GlobalScope.produce {
        val result = remoteService.auth(login, password)

        if (result) {
            localService.setAuthData(AuthData(login, password))
        }

        send(result)
    }

    override suspend fun getUserData(): ReceiveChannel<UserData?> = GlobalScope.produce {
        val localData = localService.getUserData()
        send(UserDataModelMapper().map(localData))

        val authData = localService.getAuthData()

        val remoteData = remoteService.getUserData(authData.login, authData.password)

        if (remoteData != null) {
            val userData = UserDataResponseMapper().map(remoteData)
            send(userData)
            localService.saveUserData(userData)
        }
    }
}