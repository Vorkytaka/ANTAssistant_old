package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.state.AuthState

/**
 * Авторизация пользователя
 */
class Login(
        private val repository: IRepository
) : UseCase<Credentials, AuthState>() {
    override suspend fun useCaseAction(): AuthState {
        val credentials = params ?: throw IllegalStateException()
        return repository.login(credentials).receive()
    }
}