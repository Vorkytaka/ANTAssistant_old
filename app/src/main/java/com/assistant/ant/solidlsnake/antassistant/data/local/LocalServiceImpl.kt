package com.assistant.ant.solidlsnake.antassistant.data.local

import com.assistant.ant.solidlsnake.antassistant.data.local.account.IAccountHolder
import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.data.local.pref.UserPref
import com.assistant.ant.solidlsnake.antassistant.domain.entity.AuthData
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

class LocalServiceImpl(
        private val accountHolder: IAccountHolder
) : ILocalService {
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

    override suspend fun saveUserData(data: UserData) {
        UserPref.accountName = data.accountName
        UserPref.userId = data.userId
        UserPref.state_balance = data.state.balance.toFloat()
        UserPref.state_credit = data.state.credit
        UserPref.state_downloaded = data.state.downloaded
        UserPref.state_status = data.state.status
        UserPref.tariff_name = data.tariff.name
        UserPref.tariff_downloadSpeed = data.tariff.downloadSpeed
        UserPref.tariff_uploadSpeed = data.tariff.uploadSpeed
        UserPref.tariff_price = data.tariff.price.toFloat()
    }

    override suspend fun hasAccount(): Boolean {
        return accountHolder.hasAccount()
    }

    override suspend fun getAuthData(): AuthData {
        return accountHolder.getAccount()
    }

    override suspend fun setAuthData(data: AuthData) {
        accountHolder.saveAccount(data)
    }

}