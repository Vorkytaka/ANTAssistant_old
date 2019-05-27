package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.state.CanSetCreditState

class CanSetCredit(
        private val repository: IRepository
) : UseCase<Unit, CanSetCreditState>() {
    override suspend fun useCaseAction(): CanSetCreditState {
        return repository.canSetCredit().receive()
    }
}