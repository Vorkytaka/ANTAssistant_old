package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.data.repository.RepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.Auth
import com.assistant.ant.solidlsnake.antassistant.presentation.view.AuthView
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main

class AuthPresenter : BasePresenter<AuthView>() {
    private val authUseCase = Auth(RepositoryImpl)

    fun auth(login: String, password: String) = GlobalScope.launch(Dispatchers.Main) {
        _view?.setProgress(true)

        val result = withContext(Dispatchers.IO) { authUseCase.execute(login, password) }

        _view?.setProgress(false)

        if (result) {
            _view?.success()
        } else {
            _view?.error()
        }

    }
}