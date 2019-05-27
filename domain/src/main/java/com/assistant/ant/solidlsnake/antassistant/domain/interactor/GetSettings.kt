package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.assistant.ant.solidlsnake.antassistant.domain.repository.ISettingsRepository

class GetSettings(
        private val repository: ISettingsRepository
) : UseCase<Unit, Settings>() {
    override suspend fun useCaseAction(): Settings {
        return repository.getSettings()
    }
}