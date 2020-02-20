package com.assistant.ant.solidlsnake.antassistant.presentation.presenter.factory

import com.assistant.ant.solidlsnake.antassistant.domain.interactor.*
import com.assistant.ant.solidlsnake.antassistant.mvp.MvpView
import com.assistant.ant.solidlsnake.antassistant.mvp.Presenter
import com.assistant.ant.solidlsnake.antassistant.mvp.PresenterProvider
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.AuthPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.LaunchPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.SettingsPresenter

class PresenterFactory(
        private val logicUseCase: Login,
        private val isLoggedUseCase: IsLogged,
        private val getUserDataUseCase: GetUserData,
        private val maxAvailableCreditUseCase: MaxAvailableCredit,
        private val canSetCredit: CanSetCredit,
        private val logoutUseCase: Logout,
        private val getSettingsUseCase: GetSettings,
        private val saveSettingsUseCase: SaveSettings
) : PresenterProvider.Factory {
    override fun <V : MvpView, P : Presenter<V>> create(modelClass: Class<P>): P {
        return when {
            modelClass.isAssignableFrom(AuthPresenter::class.java) -> {
                AuthPresenter(logicUseCase) as P
            }
            modelClass.isAssignableFrom(LaunchPresenter::class.java) -> {
                LaunchPresenter(isLoggedUseCase) as P
            }
            modelClass.isAssignableFrom(MainPresenter::class.java) -> {
                MainPresenter(getUserDataUseCase, maxAvailableCreditUseCase, canSetCredit) as P
            }
            modelClass.isAssignableFrom(SettingsPresenter::class.java) -> {
                SettingsPresenter(logoutUseCase, getSettingsUseCase, saveSettingsUseCase) as P
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }
}