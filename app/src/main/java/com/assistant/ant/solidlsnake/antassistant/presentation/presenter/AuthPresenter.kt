package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.data.repository.AuthRepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.Auth
import com.assistant.ant.solidlsnake.antassistant.presentation.view.AuthView
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main

class AuthPresenter : BasePresenter<AuthView>() {
    private val authUseCase = Auth(AuthRepositoryImpl)

    fun auth(login: String, password: String) = GlobalScope.launch(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) { authUseCase.execute(login, password) }

        if (result) {
            _view?.success()
        } else {
            _view?.error()
        }
    }
}