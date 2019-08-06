package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.common.UseCase
import com.assistant.ant.solidlsnake.antassistant.domain.repository.ISettingsRepository

class GetSettings(
        private val repository: ISettingsRepository
) : UseCase<Unit, Settings>() {
    override suspend fun doOnBackground(): Settings = repository.getSettings()
}