package com.assistant.ant.solidlsnake.antassistant.presentation.model

import android.support.annotation.DrawableRes
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

class UserDataUI(
        private val userData: UserData
) {
    fun getList(): List<ListObject> {
        // todo: Получение строк из ресурсов
        return listOf(
                ListObject("Учетная запись", userData.accountName, R.drawable.ic_account_name),
                ListObject("Код плательщика", userData.userId, R.drawable.ic_user_id),
                ListObject("Состояние", if (userData.state.status) "Активна" else "Неактивна", if (userData.state.status) R.drawable.ic_state_on else R.drawable.ic_state_off),
                ListObject("Навазние тарифа", userData.tariff.name, R.drawable.ic_tariff_name),
                ListObject("Цена за месяц", userData.tariff.price.toInt().toString(), R.drawable.ic_price_month),
                ListObject("Цена за день", (userData.tariff.price / 30f).toInt().toString(), R.drawable.ic_price_day),
                ListObject("Скорость загрузки", "${userData.tariff.downloadSpeed}Мб", R.drawable.ic_download_speed),
                ListObject("Скорость отдачи", "${userData.tariff.uploadSpeed}Мб", R.drawable.ic_upload_speed)
        )
    }

    inner class ListObject(
            val hint: String,
            val info: String,
            @DrawableRes val iconId: Int
    )
}