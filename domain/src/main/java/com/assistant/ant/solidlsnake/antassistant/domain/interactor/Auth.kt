package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.state.AuthState

/**
 * Авторизация пользователя
 */
class Auth(
        private val repository: IRepository
) : UseCase<Credentials, AuthState>() {
    override suspend fun execute(action: (AuthState) -> Unit) {
        val credentials = params ?: throw IllegalStateException("Params must not to be null")

        val auth = repository.auth(credentials).receive()

        val state = if (auth) {
            repository.setCredentials(credentials)
            AuthState.Success
        } else {
            AuthState.Error
        }

        action(state)
    }
}