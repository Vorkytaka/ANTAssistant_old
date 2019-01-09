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
        TODO()
    }
}