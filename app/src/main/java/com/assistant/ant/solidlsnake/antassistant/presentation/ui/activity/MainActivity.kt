package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.presentation.SimpleNavigator
import com.assistant.ant.solidlsnake.antassistant.presentation.model.UserDataUI
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter.MarginDivider
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.adapter.UserInfoAdapter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MainView {

    companion object {
        private const val MENU_EXIT_ID = 1
    }

    private val presenter: MainPresenter by inject()

    private val adapter = UserInfoAdapter()

    private val clipboardManager: ClipboardManager by lazy {
        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data.adapter = adapter
        data.layoutManager = LinearLayoutManager(baseContext)
        data.addItemDecoration(MarginDivider(3))

        adapter.itemClickListener = {
            val data = ClipData.newPlainText("ANTData", it.info.text)
            clipboardManager.primaryClip = data
            Toast.makeText(this, R.string.s_main_copy_message, Toast.LENGTH_SHORT).show()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_EXIT_ID, 0, R.string.s_main_logout)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            MENU_EXIT_ID -> {
                showLogoutDialog()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun setProgress(progress: Boolean) {
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
        adapter.setData(data.getList())
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
}
