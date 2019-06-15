package com.assistant.ant.solidlsnake.antassistant.data.local.pref

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.chibatching.kotpref.KotprefModel

object SettingsPref : KotprefModel() {
    var notification by booleanPref(false)
    var notificationHour by intPref(12)
    var notificationMinute by intPref(0)
    var notificationDays by intPref(3)

    var economyTraffic by booleanPref(false)
    var autoCredit by booleanPref(false)
}

fun SettingsPref.toSettings() = Settings(
        this.notification,
        this.notificationHour,
        this.notificationMinute,
        this.notificationDays,
        this.economyTraffic,
        this.autoCredit
)