package com.assistant.ant.solidlsnake.antassistant.presentation.view

import com.assistant.ant.solidlsnake.antassistant.presentation.model.UserDataUI

interface MainView : BaseView {
    fun setProgress(progress: Boolean)
    fun showUserData(data: UserDataUI)
    fun showCreditSnack()
}