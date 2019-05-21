package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.domain.interactor.CanSetCredit
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.GetUserData
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.MaxAvailableCredit
import com.assistant.ant.solidlsnake.antassistant.domain.state.CanSetCreditState
import com.assistant.ant.solidlsnake.antassistant.domain.state.GetUserDataState
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.coroutines.launch

class MainPresenter(
        private val getUserDataUseCase: GetUserData,
        private val maxAvailableCreditUseCase: MaxAvailableCredit,
        private val canSetCredit: CanSetCredit
) : BasePresenter<MainView>() {
    override fun doOnAttach() {
        getUserData()
        canSetCredit()
    }

    fun getUserData() = launch {
        view?.setProgress(true)

        getUserDataUseCase.execute {
            when (it) {
                is GetUserDataState.Result -> {
                    view?.showUserData(it.data)
                }
            }
        }

        view?.setProgress(false)
    }

    fun canSetCredit() = launch {
        canSetCredit.execute {
            when (it) {
                CanSetCreditState.Can -> {
                    view?.showCreditSnack()
                }
            }
        }
    }
}