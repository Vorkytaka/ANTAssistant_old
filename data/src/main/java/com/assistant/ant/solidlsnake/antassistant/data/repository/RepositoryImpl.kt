package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.local.ILocalService
import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.data.remote.IRemoteService
import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import com.assistant.ant.solidlsnake.antassistant.domain.Mapper
import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.state.*

class RepositoryImpl(
        private val remoteService: IRemoteService,
        private val localService: ILocalService,
        private val remoteMapper: Mapper<UserDataResponse, UserData>,
        private val localMapper: Mapper<UserDataModel, UserData>
) : IRepository {

    override suspend fun isLogged(): IsLoggedState {
        val credentials = localService.getCredentials()
                ?: return IsLoggedState.NoCredentialsError

        val isLogged = remoteService.auth(credentials)
        return if (isLogged) IsLoggedState.Success else IsLoggedState.AuthError
    }

    override suspend fun login(credentials: Credentials): AuthState {
        val isLogged = remoteService.auth(credentials)

        return if (isLogged) {
            localService.setCredentials(credentials)
            AuthState.Success
        } else {
            AuthState.Error
        }
    }

    override suspend fun logout() {
        localService.clear()
    }

    override suspend fun getUserData(): GetUserDataState {
        val localData = localService.getUserData()
        GetUserDataState.Result(localMapper(localData))

        localService.getCredentials()?.let { credentials ->
            remoteService.getUserData(credentials)?.let {
                val data = remoteMapper(it)
                localService.saveUserData(data)
                return GetUserDataState.Result(data)
            }
        }
        return GetUserDataState.NoUserData
    }

    override suspend fun canSetCredit(): CanSetCreditState {
        return CanSetCreditState.Cannot
    }

    override suspend fun maxAvailableCredit(): MaxAvailableCreditState {
        return MaxAvailableCreditState.Result(CreditValue.V_300)
    }

    override suspend fun setCredit(creditValue: CreditValue) {
        TODO("not implemented")
    }
}