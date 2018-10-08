package com.assistant.ant.solidlsnake.antassistant.data.remote

import com.assistant.ant.solidlsnake.antassistant.data.remote.net.Api
import com.assistant.ant.solidlsnake.antassistant.data.remote.parser.Parser
import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse

class RemoteServiceImpl(
        private val api: Api
) : IRemoteService {
    override suspend fun auth(login: String, password: String): Boolean {
        return try {
            val body = api.info(login, password).await().string()
            Parser.isLogged(body)
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getUserData(login: String, password: String): UserDataResponse? {
        return try {
            val body = api.info(login, password).await().string()
            Parser.userData(body)
        } catch (e: Exception) {
            null
        }
    }
}