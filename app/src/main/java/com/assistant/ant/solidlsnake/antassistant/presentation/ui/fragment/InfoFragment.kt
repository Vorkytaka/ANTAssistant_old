package com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.view.InfoView
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    private val clipboardManager: ClipboardManager by lazy {
        requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    private val copyClickListener = View.OnClickListener {
        if (it is InfoView) {
            val msg = ClipData.newPlainText("ANTAssistant", it.value)
            clipboardManager.primaryClip = msg
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    fun setData(data: UserData) {
        info_account_name?.value = data.accountName
        info_user_id?.value = data.userId
        info_status?.setIconId(if (data.state.status) R.drawable.ic_state_on else R.drawable.ic_state_off)
        info_status?.value = if (data.state.status) "Активна" else "Неактивна"
        info_tariff_name?.value = data.tariff.name
        info_price_month?.value = formatStringWithRuble.format(data.tariff.price)
        info_price_day?.value = formatStringWithRuble.format(data.pricePerDay)
        info_downloaded?.value = "${data.state.downloaded} Мб"
        info_download_speed?.value = "${data.tariff.downloadSpeed} Мб"
        info_upload_speed?.value = "${data.tariff.uploadSpeed} Мб"
        info_dyndns?.value = data.dynDns
    }

    companion object {
        private const val formatStringWithRuble = "%.1f \u20BD"
    }
}
