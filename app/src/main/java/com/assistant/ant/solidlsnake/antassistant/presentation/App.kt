package com.assistant.ant.solidlsnake.antassistant.presentation

import android.app.Application
import com.assistant.ant.solidlsnake.antassistant.data.account.AccountManagerHolder
import com.chibatching.kotpref.Kotpref

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        AccountManagerHolder.init(this)
        // todo: Инициализация Koin
    }
}