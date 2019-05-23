package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.GetSettings
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.Logout
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.SaveSettings
import com.assistant.ant.solidlsnake.antassistant.presentation.view.SettingsView
import kotlinx.coroutines.launch

class SettingsPresenter(
        private val logoutUseCase: Logout,
        private val getSettingsUseCase: GetSettings,
        private val saveSettingsUseCase: SaveSettings
) : BasePresenter<SettingsView>() {

    private lateinit var settings: Settings

    override fun doOnAttach() {
        launch {
            getSettingsUseCase.execute {
                settings = it
            }
        }
    }

    fun logout() = launch {
        logoutUseCase.execute {
            view?.logout()
        }
    }

    fun notificationSettings(enabled: Boolean) {
        settings = settings.copy(
                notification = enabled
        )

        launch {
            saveSettingsUseCase.params(settings)
                    .execute {
                        view?.handleSettings(it)
                    }
        }
    }
}