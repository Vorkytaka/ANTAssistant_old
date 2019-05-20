package com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment

import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.SettingsPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.SettingsView
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.ext.android.inject

class SettingsFragment : Fragment(), SettingsView {
    private val presenter: SettingsPresenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        btn_logout.setOnClickListener { showLogoutDialog() }

        settings_notification.setOnClickListener {
            val checked = !settings_notification_switcher.isChecked
            settings_notification_switcher.isChecked = checked
            TransitionManager.beginDelayedTransition(view as ViewGroup)
            settings_notification_time.visibility = if (checked) View.VISIBLE else View.GONE
            settings_notification_days.visibility = if (checked) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
                .setMessage(R.string.s_main_logout_message)
                .setPositiveButton(R.string.yes) { _, _ -> presenter.logout() }
                .setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }
                .show()
    }

    override fun logout() {
        requireActivity().finish()
    }
}
