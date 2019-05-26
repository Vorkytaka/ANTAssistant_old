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
                view?.handleSettings(settings)
            }
        }
    }

    fun logout() = launch {
        logoutUseCase.execute {
            view?.logout()
        }
    }

    fun changeNotification(enabled: Boolean) {
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

    fun changeEcconomTraffic(enabled: Boolean) {
        settings = settings.copy(
                economyTraffic = enabled
        )

        launch {
            saveSettingsUseCase.params(settings)
                    .execute {
                        view?.handleSettings(it)
                    }
        }
    }

    fun changeAutoCredit(enabled: Boolean) {
        settings = settings.copy(
                autoCredit = enabled
        )

        launch {
            saveSettingsUseCase.params(settings)
                    .execute {
                        view?.handleSettings(it)
                    }
        }
    }
}