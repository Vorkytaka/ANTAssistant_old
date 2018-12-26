package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.state.IsLoggedState

/**
 * Проверка зашел ли пользователь в систему
 */
class IsLogged(
        private val repository: IRepository
) : UseCase<Unit, IsLoggedState>() {
    override suspend fun execute(action: (IsLoggedState) -> Unit) {
        val credentials = repository.getCredentials().receiveOrNull()

        if (credentials == null) {
            action(IsLoggedState.Error)
            return
        }

        val auth = repository.auth(credentials).receive()
        action(if (auth) IsLoggedState.Success else IsLoggedState.AuthError)
    }
}