package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository

/**
 * Авторизация пользователя
 */
class Auth(
        private val repository: IRepository
) : UseCase<Auth.Params, Boolean> {
    override suspend fun execute(params: Params): Boolean = repository.auth(params.login, params.password)

    data class Params(val login: String, val password: String)
}