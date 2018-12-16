package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import kotlinx.coroutines.channels.consumeEach

/**
 * Авторизация пользователя
 */
class Auth(
        private val repository: IRepository
) : UseCase<Credentials, Boolean>() {
    override suspend fun execute(success: (Boolean) -> Unit, error: (Throwable) -> Unit) {
        if (params == null) {
            error(IllegalStateException("Params must be init"))
            return
        }

        repository.auth(params!!).consumeEach {
            success(it)
        }
    }
}