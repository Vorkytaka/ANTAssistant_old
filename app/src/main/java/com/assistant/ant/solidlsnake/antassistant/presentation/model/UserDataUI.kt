package com.assistant.ant.solidlsnake.antassistant.presentation.model

import android.support.annotation.DrawableRes
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

class ListObject(
        val hint: String,
        val info: String,
        @DrawableRes val iconId: Int
)

fun UserData.generateList() = listOf(
        ListObject("Учетная запись", this.accountName, R.drawable.ic_account_name),
        ListObject("Код плательщика", this.userId, R.drawable.ic_user_id),
        ListObject("Состояние", if (this.state.status) "Активна" else "Неактивна", if (this.state.status) R.drawable.ic_state_on else R.drawable.ic_state_off),
        ListObject("Навазние тарифа", this.tariff.name, R.drawable.ic_tariff_name),
        ListObject("Цена за месяц", this.tariff.price.toInt().toString(), R.drawable.ic_price_month),
        ListObject("Цена за день", (this.tariff.price / 30f).toInt().toString(), R.drawable.ic_price_day),
        ListObject("Скорость загрузки", "${this.tariff.downloadSpeed} Мб", R.drawable.ic_download_speed),
        ListObject("Скорость отдачи", "${this.tariff.uploadSpeed} Мб", R.drawable.ic_upload_speed),
        ListObject("Скачано за текущий месяц", "${this.state.downloaded} Мб", R.drawable.ic_downloaded),
        ListObject("Ваш DynDNS", this.dynDns, R.drawable.ic_dyn_dns)
)