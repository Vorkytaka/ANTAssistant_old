package com.assistant.ant.solidlsnake.antassistant.data.mapper

import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import com.assistant.ant.solidlsnake.antassistant.domain.entity.State
import com.assistant.ant.solidlsnake.antassistant.domain.entity.Tariff
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

fun UserDataModel.toUserData(): UserData {
    val state = State(
            this.state_balance,
            this.state_downloaded,
            this.state_status,
            this.state_credit,
            this.state_smsInfo
    )

    val tariff = Tariff(
            this.tariff_name,
            this.tariff_downloadSpeed,
            this.tariff_uploadSpeed,
            this.tariff_price
    )

    return UserData(
            this.accountName,
            this.userId,
            this.dynDns,
            tariff,
            state
    )
}

fun UserDataResponse.toUserData(): UserData {
    val state = State(
            this.state_balance,
            this.state_downloaded,
            this.state_status,
            this.state_credit,
            this.state_smsInfo
    )

    val tariff = Tariff(
            this.tariff_name,
            this.tariff_downloadSpeed,
            this.tariff_uploadSpeed,
            this.tariff_price
    )

    return UserData(
            this.accountName,
            this.userId,
            this.dynDns,
            tariff,
            state
    )
}