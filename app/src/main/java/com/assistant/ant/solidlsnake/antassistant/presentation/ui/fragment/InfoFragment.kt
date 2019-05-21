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
        tv_account_name_value.text = data.accountName
        tv_user_id_value.text = data.userId
        iv_status_image.setImageResource(if (data.state.status) R.drawable.ic_state_on else R.drawable.ic_state_off)
        tv_status_value.text = if (data.state.status) "Активна" else "Неактивна"
        tv_tariff_name_value.text = data.tariff.name
        tv_price_month_value.text = data.tariff.price.toString()
        tv_price_day_value.text = data.pricePerDay.toString()
        tv_downloaded_value.text = data.state.downloaded.toString()
        tv_download_speed_value.text = data.tariff.downloadSpeed.toString()
        tv_upload_speed_value.text = data.tariff.uploadSpeed.toString()
        tv_dyn_dns_value.text = data.dynDns
    }
}