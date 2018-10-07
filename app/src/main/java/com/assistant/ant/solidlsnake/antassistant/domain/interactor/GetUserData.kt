package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import kotlinx.coroutines.channels.consumeEach

/**
 * Полученние данных о пользователе
 */
class GetUserData(
        private val repository: IRepository
) : UseCase<Unit, UserData>() {
    override suspend fun execute(success: (UserData) -> Unit, error: (Throwable) -> Unit) {
        repository.getUserData().consumeEach {
            success(it)
        }
    }
}