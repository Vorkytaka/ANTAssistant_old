package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import android.os.Bundle
import com.assistant.ant.solidlsnake.antassistant.presentation.SimpleNavigator
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.LaunchPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.LaunchView
import org.koin.android.scope.currentScope

class LaunchActivity : BaseActivity(), LaunchView {

    private val presenter: LaunchPresenter by currentScope.inject()

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