package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.domain.interactor.CanSetCredit
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.GetUserData
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.Logout
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.MaxAvailableCredit
import com.assistant.ant.solidlsnake.antassistant.domain.state.CanSetCreditState
import com.assistant.ant.solidlsnake.antassistant.domain.state.GetUserDataState
import com.assistant.ant.solidlsnake.antassistant.presentation.model.UserDataUI
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
        private val getUserDataUseCase: GetUserData,
        private val maxAvailableCreditUseCase: MaxAvailableCredit,
        private val canSetCredit: CanSetCredit,
        private val logoutUseCase: Logout
) : BasePresenter<MainView>() {
    override fun doOnAttach() {
        getUserData()
        canSetCredit()
    }

    fun getUserData() = GlobalScope.launch(Dispatchers.Main) {
        view?.setProgress(true)

        getUserDataUseCase.execute {
            when (it) {
                is GetUserDataState.Result -> {
                    view?.showUserData(UserDataUI(it.data))
                }
            }
        }

        view?.setProgress(false)
    }

    fun canSetCredit() = GlobalScope.launch(Dispatchers.Main) {
        canSetCredit.execute {
            when (it) {
                CanSetCreditState.Can -> {
                    view?.showCreditSnack()
                }
            }
        }
    }

    fun logout() = GlobalScope.launch(Dispatchers.Main) {
        logoutUseCase.execute {
            view?.logout()
        }
    }
}