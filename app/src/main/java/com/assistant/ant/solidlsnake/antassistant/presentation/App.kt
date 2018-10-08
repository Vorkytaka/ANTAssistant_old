package com.assistant.ant.solidlsnake.antassistant.presentation

import android.app.Application
import com.assistant.ant.solidlsnake.antassistant.data.account.AccountManagerHolder
import com.assistant.ant.solidlsnake.antassistant.di.appModule
import com.chibatching.kotpref.Kotpref
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        AccountManagerHolder.init(this)
        startKoin(this, listOf(appModule))
    }
}