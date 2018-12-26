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
        val p = params ?: throw IllegalStateException()

        val auth = repository.auth(p).receive()
        action(if (auth) AuthState.Success else AuthState.Error)
    }
}