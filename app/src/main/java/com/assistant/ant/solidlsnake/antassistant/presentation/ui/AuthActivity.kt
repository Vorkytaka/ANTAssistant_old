package com.assistant.ant.solidlsnake.antassistant.presentation.ui

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.OvershootInterpolator
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

    private var animationEnd: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus) {
            startAnimation()
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

    private fun startAnimation() {
        btn_confirm.visibility = View.GONE

        val screenSize = run {
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            metrics.heightPixels
        }

        val pos = (screenSize - auth_container.top).toFloat()

        val authContainerAppearance = ObjectAnimator.ofFloat(auth_container, View.TRANSLATION_Y, pos, 0f)
        authContainerAppearance.duration = 600
        authContainerAppearance.interpolator = OvershootInterpolator(0.5f)

        val btnConfirmAppearance = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val x = btn_confirm.measuredWidth / 2
            val y = btn_confirm.measuredHeight / 2
            val endRadius = Math.max(btn_confirm.width, btn_confirm.height)

            ViewAnimationUtils.createCircularReveal(
                    btn_confirm,
                    x,
                    y,
                    0f,
                    endRadius.toFloat()
            )
        } else {
            val scaleX = ObjectAnimator.ofFloat(btn_confirm, View.SCALE_X, 0.7f, 1f)
            val scaleY = ObjectAnimator.ofFloat(btn_confirm, View.SCALE_Y, 0.7f, 1f)
            val alpha = ObjectAnimator.ofFloat(btn_confirm, View.ALPHA, 0f, 1f)

            val set = AnimatorSet()
            set.playTogether(scaleX, scaleY, alpha)

            set
        }

        btnConfirmAppearance.duration = 300

        btnConfirmAppearance.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                btn_confirm.visibility = View.VISIBLE
            }
        })

        authContainerAppearance.addUpdateListener {
            if (!animationEnd && it.animatedFraction >= 0.7f) {
                animationEnd = true
                btnConfirmAppearance.start()
            }
        }

        authContainerAppearance.start()
    }
}
