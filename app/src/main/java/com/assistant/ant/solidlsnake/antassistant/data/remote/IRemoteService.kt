package com.assistant.ant.solidlsnake.antassistant.data.remote

import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse

interface IRemoteService {
    suspend fun auth(login: String, password: String): Boolean
    suspend fun getUserData(login: String, password: String): UserDataResponse?
}