package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import kotlinx.coroutines.channels.consumeEach

class CanSetCredit(
        private val repository: IRepository
) : UseCase<Unit, Boolean>() {
    override suspend fun execute(success: (Boolean) -> Unit, error: (Throwable) -> Unit) {
        repository.canSetCredit().consumeEach {
            success(it)
        }
    }
}