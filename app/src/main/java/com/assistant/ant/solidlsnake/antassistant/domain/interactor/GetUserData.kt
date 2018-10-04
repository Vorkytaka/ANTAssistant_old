package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository

/**
 * Полученние данных о пользователе
 */
class GetUserData(
        private val repository: IRepository
) : UseCase {
    suspend fun get(): UserData = repository.getUserData()
}