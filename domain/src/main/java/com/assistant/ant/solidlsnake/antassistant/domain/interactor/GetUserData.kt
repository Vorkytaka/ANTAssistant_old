package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.state.GetUserDataState

/**
 * Полученние данных о пользователе
 */
class GetUserData(
        private val repository: IRepository
) : UseCase<Unit, GetUserDataState>() {
    override suspend fun useCaseAction(): GetUserDataState {
//        for (userData in repository.getUserData()) {
//            return userData
//        }
        return repository.getUserData().receive()
    }
}