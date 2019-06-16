package com.assistant.ant.solidlsnake.antassistant.presentation.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.assistant.ant.solidlsnake.antassistant.data.local.alarm.IAlarmUtils
import com.assistant.ant.solidlsnake.antassistant.domain.repository.ISettingsRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import org.koin.core.inject

class StartupReceiver : BroadcastReceiver(), KoinComponent {

    private val settingsRepository: ISettingsRepository by inject()
    private val alarmUtils: IAlarmUtils by inject()

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