package com.assistant.ant.solidlsnake.antassistant.domain.repository

interface IAuthRepository {
    suspend fun isLogged(): Boolean
    suspend fun auth(login: String, password: String): Boolean
}