package com.assistant.ant.solidlsnake.antassistant.data.repository

import com.assistant.ant.solidlsnake.antassistant.data.local.pref.SettingsPref
import com.assistant.ant.solidlsnake.antassistant.data.local.pref.toSettings
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
        val prevSettings = SettingsPref.toSettings()

        if (prevSettings.notification != settings.notification) {
            SettingsPref.notification = settings.notification
        }

        if (prevSettings.notificationHour != settings.notificationHour) {
            SettingsPref.notificationHour = settings.notificationHour
        }

        if (prevSettings.notificationMinute != settings.notificationMinute) {
            SettingsPref.notificationMinute = settings.notificationMinute
        }

        if (prevSettings.notificationDays != settings.notificationDays) {
            SettingsPref.notificationDays = settings.notificationDays
        }

        if (prevSettings.economyTraffic != settings.economyTraffic) {
            SettingsPref.economyTraffic = settings.economyTraffic
        }

        if (prevSettings.autoCredit != settings.autoCredit) {
            SettingsPref.autoCredit = settings.autoCredit
        }

        return settings
    }
}