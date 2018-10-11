package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import kotlinx.coroutines.channels.consumeEach

class MaxAvailableCredit(
        private val repository: IRepository
) : UseCase<Unit, CreditValue>() {
    override suspend fun execute(success: (CreditValue) -> Unit, error: (Throwable) -> Unit) {
        repository.maxAvailableCredit().consumeEach {
            success(it)
        }
    }
}