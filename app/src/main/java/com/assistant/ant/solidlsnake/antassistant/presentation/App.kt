package com.assistant.ant.solidlsnake.antassistant.presentation

import android.app.Application
import com.assistant.ant.solidlsnake.antassistant.di.appModule
import com.assistant.ant.solidlsnake.antassistant.di.dataModule
import com.assistant.ant.solidlsnake.antassistant.di.domainModule
import com.chibatching.kotpref.Kotpref
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}