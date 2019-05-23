package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.assistant.ant.solidlsnake.antassistant.domain.repository.ISettingsRepository

class SaveSettings(
        private val repository: ISettingsRepository
) : UseCase<Settings, Settings>() {
    override suspend fun execute(action: (Settings) -> Unit) {
        val params = this.params ?: throw IllegalStateException()

        // todo: validation

        action(repository.saveSettings(params))
    }
}