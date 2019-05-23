package com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.SettingsPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity.LaunchActivity
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
            presenter.notificationSettings(!settings_notification_switcher.isChecked)
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
        val intent = Intent(requireActivity(), LaunchActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }

    override fun handleSettings(settings: Settings) {

    }
}
