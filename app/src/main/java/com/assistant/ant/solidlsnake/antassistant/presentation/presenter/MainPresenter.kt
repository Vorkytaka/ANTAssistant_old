package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.data.repository.RepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.GetUserData
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch

class MainPresenter : BasePresenter<MainView>() {
    private val getUserDataUseCase = GetUserData(RepositoryImpl())

    override fun doOnAttach() {
        getUserData()
    }

    fun getUserData() = GlobalScope.launch(Dispatchers.Main) {
        _view?.setProgress(true)

        getUserDataUseCase.execute({
            _view?.showUserData(it)
        }, {})

        _view?.setProgress(false)
    }
}