package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.assistant.ant.solidlsnake.antassistant.domain.repository.ISettingsRepository

class SaveSettings(
        private val repository: ISettingsRepository
) : UseCase<Settings, Settings>() {
    override suspend fun doOnBackground(): Settings {
        val params = this.params ?: throw IllegalStateException()

        // todo: validation

        return repository.saveSettings(params)
    }
}