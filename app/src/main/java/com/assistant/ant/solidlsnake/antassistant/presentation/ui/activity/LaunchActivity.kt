package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import android.os.Bundle
import com.assistant.ant.solidlsnake.antassistant.di.applicationModule
import com.assistant.ant.solidlsnake.antassistant.mvp.PresenterProvider
import com.assistant.ant.solidlsnake.antassistant.presentation.SimpleNavigator
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.LaunchPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.LaunchView

class LaunchActivity : BaseActivity<LaunchView, LaunchPresenter>(), LaunchView {

    override val presenterClazz: Class<LaunchPresenter> = LaunchPresenter::class.java
    override val presenterFactory: PresenterProvider.Factory? = applicationModule.presenterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun openAuthScreen() {
        overridePendingTransition(0, 0)
        SimpleNavigator.goToAuthScreen(this)
    }

    override fun openMainScreen() {
        SimpleNavigator.goToMainScreen(this)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}