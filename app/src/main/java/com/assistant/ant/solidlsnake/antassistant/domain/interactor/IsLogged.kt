package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IAuthRepository

class IsLogged(
        private val repository: IAuthRepository
) : UseCase {
    suspend fun check(): Boolean {
        return repository.isLogged()
    }
}