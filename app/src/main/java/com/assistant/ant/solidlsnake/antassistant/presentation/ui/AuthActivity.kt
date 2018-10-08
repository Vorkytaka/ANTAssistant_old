package com.assistant.ant.solidlsnake.antassistant.presentation.ui

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.app.Activity
import android.os.Bundle
import android.view.View
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.orEmpty
import com.assistant.ant.solidlsnake.antassistant.presentation.SimpleNavigator
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.AuthPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.AuthView
import kotlinx.android.synthetic.main.activity_auth.*
import org.koin.android.ext.android.inject

class AuthActivity : BaseActivity(), AuthView {

    private val presenter: AuthPresenter by inject()

    private var mAccountAuthenticatorResponse: AccountAuthenticatorResponse? = null
    private var mResultBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAccountAuthenticatorResponse = intent.getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE)
        mAccountAuthenticatorResponse?.onRequestContinued()

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

    override fun finish() {
        if (mResultBundle != null) {
            mAccountAuthenticatorResponse?.onResult(mResultBundle)
        } else {
            mAccountAuthenticatorResponse?.onError(AccountManager.ERROR_CODE_CANCELED, "canceled")
        }
        mAccountAuthenticatorResponse = null
        super.finish()
    }

    override fun success() {
        setResult(Activity.RESULT_OK, intent)
        SimpleNavigator.goToMainScreen(this)
    }

    override fun error() {
        et_error.visibility = View.VISIBLE
    }

    override fun setProgress(progress: Boolean) {
        et_login.isEnabled = !progress
        et_password.isEnabled = !progress
        btn_confirm.isEnabled = !progress
    }
}
