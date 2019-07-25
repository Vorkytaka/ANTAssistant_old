package com.assistant.ant.solidlsnake.antassistant.presentation.view

import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

interface MainView : BaseView {
    fun setProgress(progress: Boolean)
    fun showUserData(data: UserData)
    fun showCachedTime(time: Long)
    fun showCreditSnack()
    fun logout()
}