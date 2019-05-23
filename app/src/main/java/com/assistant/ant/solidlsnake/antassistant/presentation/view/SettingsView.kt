package com.assistant.ant.solidlsnake.antassistant.presentation.view

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings

interface SettingsView : BaseView {
    fun logout()
    fun handleSettings(settings: Settings)
}