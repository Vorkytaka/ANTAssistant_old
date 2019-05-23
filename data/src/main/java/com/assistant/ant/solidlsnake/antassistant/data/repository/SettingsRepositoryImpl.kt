package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.local.pref.SettingsPref
import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.assistant.ant.solidlsnake.antassistant.domain.repository.ISettingsRepository

class SettingsRepositoryImpl : ISettingsRepository {
    override suspend fun getSettings(): Settings {
        return Settings(
                SettingsPref.notification,
                SettingsPref.notificationHour,
                SettingsPref.notificationMinute,
                SettingsPref.notificationDays,
                SettingsPref.economyTraffic,
                SettingsPref.autoCredit
        )
    }

    override suspend fun saveSettings(settings: Settings): Settings {
        SettingsPref.notification = settings.notification
        SettingsPref.notificationHour = settings.notificationHour
        SettingsPref.notificationMinute = settings.notificationMinute
        SettingsPref.notificationDays = settings.notificationDays
        SettingsPref.economyTraffic = settings.economyTraffic
        SettingsPref.autoCredit = settings.autoCredit

        return settings
    }
}