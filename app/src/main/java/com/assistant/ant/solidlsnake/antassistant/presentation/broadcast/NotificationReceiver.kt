package com.assistant.ant.solidlsnake.antassistant.presentation.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.data.local.ILocalService
import com.assistant.ant.solidlsnake.antassistant.data.mapper.toUserData
import com.assistant.ant.solidlsnake.antassistant.di.applicationModule
import kotlinx.coroutines.runBlocking

class NotificationReceiver : BroadcastReceiver() {

    private val localService: ILocalService = applicationModule.localService

    override fun onReceive(context: Context, intent: Intent?) = runBlocking {
        val userData = localService.getUserData()
                ?: return@runBlocking

        val daysLeft = userData.toUserData().daysLeft
        val message = context.resources.getQuantityString(
                R.plurals.notification_internet_out_message,
                daysLeft,
                daysLeft
        )

        val channelId = context.getString(R.string.notification_channel_main_id)
        val notification = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

        NotificationManagerCompat.from(context)
                .notify(0, notification)
    }
}