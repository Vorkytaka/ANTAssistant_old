package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.domain.interactor.GetUserData
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.MaxAvailableCredit
import com.assistant.ant.solidlsnake.antassistant.presentation.model.UserDataUI
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
        private val getUserDataUseCase: GetUserData,
        private val maxAvailableCreditUseCase: MaxAvailableCredit
) : BasePresenter<MainView>() {
    override fun doOnAttach() {
        getUserData()
    }

    fun getUserData() = GlobalScope.launch(Dispatchers.Main) {
        _view?.setProgress(true)

        getUserDataUseCase.execute({
            _view?.showUserData(UserDataUI(it))
        }, {})

        _view?.setProgress(false)
    }
}