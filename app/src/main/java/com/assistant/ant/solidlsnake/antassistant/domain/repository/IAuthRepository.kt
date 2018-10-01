package com.assistant.ant.solidlsnake.antassistant.domain.repository

interface IAuthRepository {
    suspend fun isLogged(): Boolean
}