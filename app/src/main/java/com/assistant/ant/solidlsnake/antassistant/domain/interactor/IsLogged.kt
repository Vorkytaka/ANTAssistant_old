package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository

class IsLogged(
        private val repository: IRepository
) : UseCase {
    suspend fun check(): Boolean {
        return repository.isLogged()
    }
}