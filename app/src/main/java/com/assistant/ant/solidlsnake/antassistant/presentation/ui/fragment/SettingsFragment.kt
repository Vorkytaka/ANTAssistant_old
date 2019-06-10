package com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.domain.entity.Settings
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.SettingsPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity.LaunchActivity
import com.assistant.ant.solidlsnake.antassistant.presentation.view.SettingsView
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.scope.currentScope

class SettingsFragment : Fragment(), SettingsView {

    private val presenter: SettingsPresenter by currentScope.inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        btn_logout.setOnClickListener { showLogoutDialog() }

        settings_notification.setOnClickListener {
            presenter.changeNotification(!settings_notification_switcher.isChecked)
        }
        settings_notification_time.setOnClickListener {
            TimePickerDialog(
                    activity,
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        presenter.changeNotificationTime(hour, minute)
                    },
                    12,
                    0,
                    true
            ).show()
        }
        settings_notification_days.setOnClickListener {
            val numberPicker = NumberPicker(activity)
            numberPicker.wrapSelectorWheel = false
            numberPicker.minValue = 1
            numberPicker.maxValue = 7
            numberPicker.value = 3

            AlertDialog.Builder(requireActivity())
                    .setTitle("За сколько дней уведомлять")
                    .setView(numberPicker)
                    .setPositiveButton(android.R.string.ok) { dialog, _ ->
                        presenter.changeNotificationDays(numberPicker.value)
                        dialog.dismiss()
                    }
                    .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
                    .show()
        }
        settings_traffic.setOnClickListener {
            presenter.changeEcconomTraffic(!settings_traffic_switcher.isChecked)
        }
        settings_auto_credit.setOnClickListener {
            presenter.changeAutoCredit(!settings_auto_credit_switcher.isChecked)
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
        settings_notification_switcher.isChecked = settings.notification

        val visibility = if (settings.notification) View.VISIBLE else View.GONE
        TransitionManager.beginDelayedTransition(view as ViewGroup)
        settings_notification_days.visibility = visibility
        settings_notification_time.visibility = visibility

        settings_traffic_switcher.isChecked = settings.economyTraffic
        settings_auto_credit_switcher.isChecked = settings.autoCredit

        settings_notification_time_value.text = "%02d:%02d".format(settings.notificationHour, settings.notificationMinute)
        settings_notification_days_value.text = settings.notificationDays.toString()
    }
}
