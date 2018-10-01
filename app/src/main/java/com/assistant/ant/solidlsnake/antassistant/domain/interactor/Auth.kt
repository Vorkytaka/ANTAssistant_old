package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IAuthRepository

class Auth(
        private val repository: IAuthRepository
) : UseCase {
    suspend fun execute(login: String, password: String): Boolean {
        return repository.auth(login, password)
    }
}