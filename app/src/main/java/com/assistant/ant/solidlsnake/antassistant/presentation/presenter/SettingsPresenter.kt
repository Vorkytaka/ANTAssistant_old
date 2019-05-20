package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.domain.interactor.Logout
import com.assistant.ant.solidlsnake.antassistant.presentation.view.SettingsView
import kotlinx.coroutines.launch

class SettingsPresenter(
        private val logoutUseCase: Logout
) : BasePresenter<SettingsView>() {
    fun logout() = launch {
        logoutUseCase.execute {
            view?.logout()
        }
    }
}