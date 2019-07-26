package com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    private val clipboardManager: ClipboardManager by lazy {
        requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    fun setData(data: UserData) {
        info_account_name?.setValue(data.accountName)
        info_user_id?.setValue(data.userId)
        info_status?.setIcon(if (data.state.status) R.drawable.ic_state_on else R.drawable.ic_state_off)
        info_status?.setValue(if (data.state.status) "Активна" else "Неактивна")
        info_tariff_name?.setValue(data.tariff.name)
        info_price_month?.setValue(formatStringWithRuble.format(data.tariff.price))
        info_price_day?.setValue(formatStringWithRuble.format(data.pricePerDay))
        info_downloaded?.setValue("${data.state.downloaded} Мб")
        info_download_speed?.setValue("${data.tariff.downloadSpeed} Мб")
        info_upload_speed?.setValue("${data.tariff.uploadSpeed} Мб")
        info_dyndns?.setValue(data.dynDns)
    }

    companion object {
        private const val formatStringWithRuble = "%.1f \u20BD"
    }
}
