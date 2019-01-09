package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.state.MaxAvailableCreditState

class MaxAvailableCredit(
        private val repository: IRepository
) : UseCase<Unit, MaxAvailableCreditState>() {
    override suspend fun execute(action: (MaxAvailableCreditState) -> Unit) {
        action(repository.maxAvailableCredit().receive())
    }
}