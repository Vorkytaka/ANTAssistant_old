package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.domain.interactor.IsLogged
import com.assistant.ant.solidlsnake.antassistant.domain.state.IsLoggedState
import com.assistant.ant.solidlsnake.antassistant.presentation.view.LaunchView
import kotlinx.coroutines.launch

class LaunchPresenter(
        private val isLoggedUseCase: IsLogged
) : BasePresenter<LaunchView>() {

    override fun doOnAttach() {
        checkAuth()
    }

    private fun checkAuth() = launch {
        isLoggedUseCase.execute {
            when (it) {
                is IsLoggedState.AuthError -> view?.openMainScreen()
                is IsLoggedState.Success -> view?.openMainScreen()
                is IsLoggedState.NoCredentialsError -> view?.openAuthScreen()
            }
        }
    }
}