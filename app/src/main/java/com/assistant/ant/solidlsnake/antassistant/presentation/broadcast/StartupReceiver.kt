package com.assistant.ant.solidlsnake.antassistant.presentation.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.assistant.ant.solidlsnake.antassistant.data.local.alarm.IAlarmUtils
import com.assistant.ant.solidlsnake.antassistant.di.applicationModule
import com.assistant.ant.solidlsnake.antassistant.domain.repository.ISettingsRepository
import kotlinx.coroutines.runBlocking

class StartupReceiver : BroadcastReceiver() {

    private val settingsRepository: ISettingsRepository = applicationModule.settingsRepository
    private val alarmUtils: IAlarmUtils = applicationModule.alarmUtils

    override fun onReceive(context: Context, intent: Intent) = runBlocking {
        val action = intent.action

        if (Intent.ACTION_BOOT_COMPLETED == action) {
            val needNotification = settingsRepository.getSettings().notification

            if (needNotification) {
                alarmUtils.setAlarm()
            }
        }
    }
}