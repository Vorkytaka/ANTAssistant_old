package com.assistant.ant.solidlsnake.antassistant.data.mapper

import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import com.assistant.ant.solidlsnake.antassistant.domain.Mapper
import com.assistant.ant.solidlsnake.antassistant.domain.entity.State
import com.assistant.ant.solidlsnake.antassistant.domain.entity.Tariff
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

class UserDataResponseMapper : Mapper<UserDataResponse, UserData> {
    override fun invoke(from: UserDataResponse): UserData {
        val state = State(
                from.state_balance,
                from.state_downloaded,
                from.state_status,
                from.state_credit,
                from.state_smsInfo
        )

        val tariff = Tariff(
                from.tariff_name,
                from.tariff_downloadSpeed,
                from.tariff_uploadSpeed,
                from.tariff_price
        )

        return UserData(
                from.accountName,
                from.userId,
                from.dynDns,
                tariff,
                state
        )
    }
}

