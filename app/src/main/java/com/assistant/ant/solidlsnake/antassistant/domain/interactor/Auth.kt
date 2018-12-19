package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.state.AuthState
import kotlinx.coroutines.channels.consumeEach

/**
 * Авторизация пользователя
 */
class Auth(
        private val repository: IRepository
) : UseCase<Credentials, AuthState>() {
    override suspend fun execute(action: (AuthState) -> Unit) {
        if (params == null) {
            throw IllegalStateException()
        }

        repository.auth(params!!).consumeEach {
            action(if (it) AuthState.Success else AuthState.Error)
        }
    }
}