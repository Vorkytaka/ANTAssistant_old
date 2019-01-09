package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository

class Logout(
        private val repository: IRepository
) : UseCase<Nothing, Unit>() {
    override suspend fun execute(action: (Unit) -> Unit) {
        action(repository.logout().receive())
    }
}