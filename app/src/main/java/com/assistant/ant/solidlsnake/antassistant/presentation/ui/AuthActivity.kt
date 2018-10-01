package com.assistant.ant.solidlsnake.antassistant.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.orEmpty
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.AuthPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.AuthView
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : BaseActivity(), AuthView {
    companion object {
        @JvmStatic
        fun getIntent(activity: Activity): Intent {
            return Intent(activity, AuthActivity::class.java)
        }
    }

    private val presenter = AuthPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        btn_confirm.setOnClickListener {
            val login = et_login.text.orEmpty()
            val password = et_password.text.orEmpty()
            presenter.auth(login, password)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun success() {
        // todo: проброс на главный экран
    }

    override fun error() {
        // todo: отображать ошибку
    }

    override fun setProgress(progress: Boolean) {
        et_login.isEnabled = !progress
        et_password.isEnabled = !progress
        btn_confirm.isEnabled = !progress
    }
}
