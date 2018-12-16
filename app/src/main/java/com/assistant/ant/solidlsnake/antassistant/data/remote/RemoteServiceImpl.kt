package com.assistant.ant.solidlsnake.antassistant.data.remote

import com.assistant.ant.solidlsnake.antassistant.data.remote.net.Api
import com.assistant.ant.solidlsnake.antassistant.data.remote.parser.Parser
import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue

class RemoteServiceImpl(
        private val api: Api,
        private val parser: Parser
) : IRemoteService {
    override suspend fun auth(credentials: Credentials): Boolean {
        return try {
            val body = api.info(credentials.login, credentials.password).await().string()
            parser.isLogged(body)
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getUserData(credentials: Credentials): UserDataResponse? {
        return try {
            val body = api.info(credentials.login, credentials.password).await().string()
            parser.userData(body)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun setCredit(credentials: Credentials, creditValue: CreditValue): Boolean {
        return try {
            val body = api.credit(credentials.login, credentials.password, creditValue).await().string()
            parser.isCreditSet(body)
        } catch (e: Exception) {
            false
        }
    }
}