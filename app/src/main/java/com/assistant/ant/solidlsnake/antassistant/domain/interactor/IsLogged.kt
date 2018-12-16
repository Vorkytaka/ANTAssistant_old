package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository

/**
 * Проверка зашел ли пользователь в систему
 */
class IsLogged(
        private val repository: IRepository
) : UseCase<Unit, Boolean>() {
    override suspend fun execute(success: (Boolean) -> Unit, error: (Throwable) -> Unit) {
        val credentials = repository.getCredentials().receiveOrNull()

        if (credentials == null) {
            success(false)
            return
        }

        val auth = repository.auth(credentials).receive()
        success(auth)
    }
}