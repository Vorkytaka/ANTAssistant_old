package com.assistant.ant.solidlsnake.antassistant.presentation.presenter.factory

import com.assistant.ant.solidlsnake.antassistant.domain.interactor.Login
import com.assistant.ant.solidlsnake.antassistant.mvp.MvpView
import com.assistant.ant.solidlsnake.antassistant.mvp.Presenter
import com.assistant.ant.solidlsnake.antassistant.mvp.PresenterProvider
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.AuthPresenter

class AuthPresenterFactory(
        private val loginUseCase: Login
) : PresenterProvider.Factory {
    override fun <V : MvpView, P : Presenter<V>> create(modelClass: Class<P>): P {
        require(modelClass.isAssignableFrom(AuthPresenter::class.java))
        return AuthPresenter(loginUseCase) as P
    }
}