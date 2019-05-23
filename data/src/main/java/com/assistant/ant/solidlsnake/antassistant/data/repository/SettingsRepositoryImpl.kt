package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.assistant.ant.solidlsnake.antassistant.domain.repository.ISettingsRepository

class SettingsRepositoryImpl : ISettingsRepository {
    override suspend fun getSettings(): Settings {
        return Settings(
                false, 12, 0, 0, true, true
        )
    }

    override suspend fun saveSettings(settings: Settings): Settings {
        return Settings(
                false, 12, 0, 0, true, true
        )
    }
}