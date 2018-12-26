package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.presentation.model.UserDataUI
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter.MarginDivider
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter.UserInfoAdapter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_credit.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainView {

    private val presenter: MainPresenter by inject()

    private val adapter = UserInfoAdapter()

    private val clipboardManager: ClipboardManager by lazy {
        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        data.adapter = adapter
        data.layoutManager = LinearLayoutManager(baseContext)
        data.addItemDecoration(MarginDivider(3))

        adapter.itemClickListener = {
            val data = ClipData.newPlainText("ANTData", it.info.text)
            clipboardManager.primaryClip = data
            Toast.makeText(this, R.string.s_main_copy_message, Toast.LENGTH_SHORT).show()
        }

        val credit = BottomSheetBehavior.from(bs_credit)

        bs_credit_header.setOnClickListener {
            credit.state = BottomSheetBehavior.STATE_EXPANDED
        }

        credit.state = BottomSheetBehavior.STATE_SETTLING
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

    }

    override fun showUserData(data: UserDataUI) {
        ctoolbar.title = data.userData.state.balance.toString() + " \u20BD"
        val credit = data.userData.state.credit
        val balance = data.userData.state.balance
        val payForDay = data.userData.tariff.price / 30

        // todo: Проверить правильный подсчет дней
        val daysLeft = balance / payForDay
        tv_days_left.text = daysLeft.toInt().toString()

        tv_credit.text = credit.toString()
        adapter.setData(data.getList())
    }
}
