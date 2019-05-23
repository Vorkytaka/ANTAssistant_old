package com.assistant.ant.solidlsnake.antassistant.domain.entity

data class Settings(
        val notification: Boolean,
        val notificationHour: Int,
        val notificationMinute: Int,
        val notificationDays: Int,

        val economyTraffic: Boolean,
        val autoCredit: Boolean
)