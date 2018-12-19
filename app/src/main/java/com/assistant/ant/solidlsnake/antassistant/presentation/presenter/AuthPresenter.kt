package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.Auth
import com.assistant.ant.solidlsnake.antassistant.domain.state.AuthState
import com.assistant.ant.solidlsnake.antassistant.presentation.view.AuthView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthPresenter(private val authUseCase: Auth) : BasePresenter<AuthView>() {

    fun auth(login: String, password: String) = GlobalScope.launch(Dispatchers.Main) {
        _view?.setProgress(true)

        authUseCase.params(Credentials(login, password))
                .execute {
                    when (it) {
                        is AuthState.Success -> _view?.success()
                        is AuthState.Error -> _view?.error()
                    }
                }

        _view?.setProgress(false)
    }
}