package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.state.IsLoggedState

/**
 * Проверка зашел ли пользователь в систему
 */
class IsLogged(
        private val repository: IRepository
) : UseCase<Unit, IsLoggedState>() {
    override suspend fun execute(success: (IsLoggedState) -> Unit, error: (Throwable) -> Unit) {
        val credentials = repository.getCredentials().receiveOrNull()

        if (credentials == null) {
            success(IsLoggedState.Error)
            return
        }

        val auth = repository.auth(credentials).receive()
        success(if (auth) IsLoggedState.Success else IsLoggedState.AuthError)
    }
}