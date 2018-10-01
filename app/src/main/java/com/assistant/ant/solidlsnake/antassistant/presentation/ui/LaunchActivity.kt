package com.assistant.ant.solidlsnake.antassistant.presentation.ui

import android.os.Bundle
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.LaunchPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.LaunchView

class LaunchActivity : BaseActivity(), LaunchView {
    private val presenter = LaunchPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun openAuthScreen() {
        TODO("not implemented")
    }

    override fun openMainScreen() {
        TODO("not implemented")
    }
}