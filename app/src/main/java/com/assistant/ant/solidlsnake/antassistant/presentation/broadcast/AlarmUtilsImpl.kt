package com.assistant.ant.solidlsnake.antassistant.presentation.broadcast

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.assistant.ant.solidlsnake.antassistant.data.local.alarm.IAlarmUtils
import com.assistant.ant.solidlsnake.antassistant.data.local.pref.SettingsPref
import java.util.*

class AlarmUtilsImpl(
        private val context: Context
) : IAlarmUtils {

    companion object {
        private const val ALARM_ID = 0
    }

    private val alarmManager by lazy {
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    override fun setAlarm() {
        val intent = Intent(context, NotificationReceiver::class.java)
        intent.action = "antassistant.InternetEnds"
        val pIntent = PendingIntent.getBroadcast(context, ALARM_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val triggerAtMillis = run {
            val hour = SettingsPref.notificationHour
            val minute = SettingsPref.notificationMinute

            val calendar = Calendar.getInstance()
            if (calendar.get(Calendar.HOUR_OF_DAY) > hour || (calendar.get(Calendar.HOUR_OF_DAY) == hour && calendar.get(Calendar.MINUTE) >= minute)) {
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            }
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)

            calendar.timeInMillis
        }

        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                AlarmManager.INTERVAL_DAY,
                pIntent
        )
    }

    override fun cancelAlarm() {
        val intent = Intent(context, NotificationReceiver::class.java)
        val pIntent = PendingIntent.getBroadcast(context, ALARM_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager.cancel(pIntent)
    }

}