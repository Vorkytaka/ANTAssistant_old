package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.domain.interactor.CanSetCredit
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.GetUserData
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch

class MainPresenter(
        private val getUserDataUseCase: GetUserData,
        private val canSetCreditUseCase: CanSetCredit
) : BasePresenter<MainView>() {
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