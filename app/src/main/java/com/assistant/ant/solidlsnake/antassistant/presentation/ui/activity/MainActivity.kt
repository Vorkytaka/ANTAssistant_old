package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AlertDialog
import android.view.View
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.presentation.SimpleNavigator
import com.assistant.ant.solidlsnake.antassistant.presentation.model.UserDataUI
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment.InfoFragment
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.toInfinite
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainView {

    private val presenter: MainPresenter by inject()

    private val updateAnimator by lazy {
        ObjectAnimator.ofFloat(btn_update, View.ROTATION, 0f, -360f)
                .setDuration(1000)
                .toInfinite()
                .addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        btn_update.isEnabled = false
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        btn_update.isEnabled = true
                    }
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_view_pager.adapter = Adapter(supportFragmentManager)

        btn_update.setOnClickListener {
            presenter.getUserData()
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

    override fun setProgress(progress: Boolean) {
        if (progress) {
            updateAnimator.start()
        } else {
            updateAnimator.endOnNext()
        }
    }

    override fun showUserData(data: UserDataUI) {
        tv_deposit_value.text = data.userData.state.balance.toString() + " \u20BD"
        val credit = data.userData.state.credit
        val balance = data.userData.state.balance
        val payForDay = data.userData.tariff.price / 30

//         todo: Проверить правильный подсчет дней
        val daysLeft = balance / payForDay
        tv_days_value.text = daysLeft.toInt().toString()

        tv_credit_value.text = credit.toString()
//        adapter.setData(data.getList())
    }

    override fun showCreditSnack() {
//        creditBottomSheet.state = BottomSheetBehavior.STATE_SETTLING
    }

    override fun logout() {
        SimpleNavigator.goToAuthScreen(this)
        finish()
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
                .setMessage(R.string.s_main_logout_message)
                .setPositiveButton(R.string.yes) { _, _ -> presenter.logout() }
                .setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }
                .show()
    }

    private class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {
            return InfoFragment()
        }

        override fun getCount(): Int = 1
    }
}
