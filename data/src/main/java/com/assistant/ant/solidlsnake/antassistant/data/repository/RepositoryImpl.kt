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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

class RepositoryImpl(
        private val remoteService: IRemoteService,
        private val localService: ILocalService,
        private val remoteMapper: Mapper<UserDataResponse, UserData>,
        private val localMapper: Mapper<UserDataModel, UserData>
) : IRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override suspend fun isLogged(): ReceiveChannel<IsLoggedState> {
        TODO("not implemented")
    }

    override suspend fun login(credentials: Credentials): ReceiveChannel<AuthState> {
        TODO("not implemented")
    }

    override suspend fun logout(): ReceiveChannel<Nothing> {
        TODO("not implemented")
    }

    override suspend fun getUserData(): ReceiveChannel<GetUserDataState> {
        TODO("not implemented")
    }

    override suspend fun canSetCredit(): ReceiveChannel<CanSetCreditState> {
        TODO("not implemented")
    }

    override suspend fun maxAvailableCredit(): ReceiveChannel<MaxAvailableCreditState> {
        TODO("not implemented")
    }

    override suspend fun setCredit(creditValue: CreditValue): ReceiveChannel<Nothing> {
        TODO("not implemented")
    }
}