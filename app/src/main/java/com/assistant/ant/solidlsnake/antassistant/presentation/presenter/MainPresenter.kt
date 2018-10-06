package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.data.repository.RepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.GetUserData
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main

class MainPresenter : BasePresenter<MainView>() {
    private val getUserDataUseCase = GetUserData(RepositoryImpl)

    override fun doOnAttach() {
        getUserData()
    }

    fun getUserData() = GlobalScope.launch(Dispatchers.Main) {
        _view?.setProgress(true)

        val data = withContext(Dispatchers.IO) { getUserDataUseCase.execute(Unit) }

        _view?.setProgress(false)
        _view?.showUserData(data)
    }
}