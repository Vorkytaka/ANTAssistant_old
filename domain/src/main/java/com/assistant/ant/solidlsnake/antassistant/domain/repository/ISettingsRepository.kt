package com.assistant.ant.solidlsnake.antassistant.domain.repository

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings

interface ISettingsRepository {
    suspend fun getSettings(): Settings
    suspend fun saveSettings(settings: Settings): Settings
}