package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.presentation.SimpleNavigator
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment.InfoFragment
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment.SettingsFragment
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

    private val infoFragment: InfoFragment = InfoFragment()

    private val settingsFragment: SettingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_view_pager.adapter = Adapter(supportFragmentManager)

        btn_update.setOnClickListener {
            presenter.getUserData()
        }

        menu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    main_view_pager.currentItem = 0
                    true
                }
                R.id.settings -> {
                    main_view_pager.currentItem = 1
                    true
                }
                else -> {
                    false
                }
            }
        }

        main_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(p0: Int) {
                when (p0) {
                    0 -> {
                        menu.selectedItemId = R.id.home
                    }
                    1 -> {
                        menu.selectedItemId = R.id.settings
                    }
                }
            }
        })
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

    override fun showUserData(data: UserData) {
        tv_deposit_value.text = "%.1f \u20BD".format(data.state.balance)
        val credit = data.state.credit

        tv_days_value.text = data.daysLeft.toString()

        tv_credit_value.text = credit.toString()
        infoFragment.setData(data)
    }

    override fun showCreditSnack() {
//        creditBottomSheet.state = BottomSheetBehavior.STATE_SETTLING
    }

    override fun logout() {
        SimpleNavigator.goToAuthScreen(this)
        finish()
    }

    private inner class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(item: Int): Fragment {
            return when (item) {
                0 -> infoFragment
                1 -> settingsFragment
                else -> throw IllegalStateException()
            }
        }

        override fun getCount(): Int = 2
    }
}
