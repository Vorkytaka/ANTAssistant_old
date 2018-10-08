package com.assistant.ant.solidlsnake.antassistant.data.local

import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.data.local.pref.UserPref

class LocalServiceImpl : ILocalService {
    override suspend fun getUserData(): UserDataModel {
        val data = UserDataModel()

        data.accountName = UserPref.accountName
        data.userId = UserPref.userId
        data.state_balance = UserPref.state_balance.toDouble()
        data.state_credit = UserPref.state_credit
        data.state_downloaded = UserPref.state_downloaded
        data.state_status = UserPref.state_status
        data.tariff_name = UserPref.tariff_name
        data.tariff_downloadSpeed = UserPref.tariff_downloadSpeed
        data.tariff_uploadSpeed = UserPref.tariff_uploadSpeed
        data.tariff_price = UserPref.tariff_price.toDouble()

        return data
    }
}