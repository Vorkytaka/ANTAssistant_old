package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.data.repository.RepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.IsLogged
import com.assistant.ant.solidlsnake.antassistant.presentation.view.LaunchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch

class LaunchPresenter : BasePresenter<LaunchView>() {
    private val isLoggedUseCase = IsLogged(RepositoryImpl)

    override fun doOnAttach() {
        checkAuth()
    }

    private fun checkAuth() = GlobalScope.launch(Dispatchers.Main) {
        isLoggedUseCase.execute({
            if (it) {
                _view?.openMainScreen()
            } else {
                _view?.openAuthScreen()
            }
        }, {})
    }
}