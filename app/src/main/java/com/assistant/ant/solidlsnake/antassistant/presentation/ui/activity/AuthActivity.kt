package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.EditorInfo
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.di.applicationModule
import com.assistant.ant.solidlsnake.antassistant.mvp.PresenterProvider
import com.assistant.ant.solidlsnake.antassistant.orEmpty
import com.assistant.ant.solidlsnake.antassistant.presentation.SimpleNavigator
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.AuthPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.factory.AuthPresenterFactory
import com.assistant.ant.solidlsnake.antassistant.presentation.view.AuthView
import com.assistant.ant.solidlsnake.antassistant.presentation.worker.UpdateDataWorker
import kotlinx.android.synthetic.main.activity_auth.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.max

class AuthActivity : BaseActivity<AuthView, AuthPresenter>(), AuthView {

    override val presenterClazz: Class<AuthPresenter> = AuthPresenter::class.java
    override val presenterFactory: PresenterProvider.Factory = AuthPresenterFactory(applicationModule.loginUseCase)

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

        tv_phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+74959409211")
            startActivity(intent)
        }

        et_password.setOnEditorActionListener { _, actionId, _ ->
            if (EditorInfo.IME_ACTION_DONE == actionId) {
                if (btn_confirm.isEnabled) {
                    btn_confirm.performClick()
                }

                true
            } else {
                false
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus && !animationEnd) {
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
        initUpdater()
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

        val btnConfirmAppearance = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val x = btn_confirm.measuredWidth / 2
            val y = btn_confirm.measuredHeight / 2
            val endRadius = max(btn_confirm.width, btn_confirm.height)

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
        btnConfirmAppearance.startDelay = 400

        btnConfirmAppearance.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                btn_confirm.visibility = View.VISIBLE
            }
        })

        val viewAppearance = ObjectAnimator.ofFloat(view, View.SCALE_Y, 3f, 1f)
        viewAppearance.duration = 600

        val startAnimation = AnimatorSet()
        startAnimation.playTogether(authContainerAppearance, viewAppearance, btnConfirmAppearance)
        startAnimation.interpolator = OvershootInterpolator(0.5f)
        startAnimation.start()
    }

    private fun initUpdater() {
        // Устанавливаем время на 00.01.00:00 следующего дня
        val next = run {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 1)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)

            calendar.time.time
        }

        val now = System.currentTimeMillis()

        val delay = next - now

        val request = OneTimeWorkRequest.Builder(UpdateDataWorker::class.java)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .build()

        WorkManager.getInstance().enqueue(request)
    }
}
