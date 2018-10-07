package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.data.repository.RepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.Auth
import com.assistant.ant.solidlsnake.antassistant.presentation.view.AuthView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch

class AuthPresenter : BasePresenter<AuthView>() {
    private val authUseCase = Auth(RepositoryImpl)

    fun auth(login: String, password: String) = GlobalScope.launch(Dispatchers.Main) {
        _view?.setProgress(true)

        authUseCase.params(Auth.Params(login, password))
                .execute({
                    if (it) {
                        _view?.success()
                    } else {
                        _view?.error()
                    }
                }, {})

        _view?.setProgress(false)
    }
}