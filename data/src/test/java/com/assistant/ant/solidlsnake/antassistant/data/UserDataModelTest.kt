package com.assistant.ant.solidlsnake.antassistant.data

import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.data.mapper.toUserData
import org.junit.Assert
import org.junit.Test

class UserDataModelTest {
    @Test
    fun `Mapping`() {
        val from = UserDataModel().apply {
            accountName = "noua_2_187"
            userId = "1234567890"
            dynDns = "123.a-n-t.ru"

            state_balance = 123.45
            state_downloaded = 421
            state_status = true
            state_credit = 200
            state_smsInfo = true

            tariff_name = "VX_SOMETHING"
            tariff_downloadSpeed = 74
            tariff_uploadSpeed = 47
            tariff_price = 789.01
        }

        val result = from.toUserData()

        Assert.assertEquals(result.accountName, "noua_2_187")
        Assert.assertEquals(result.userId, "1234567890")
        Assert.assertEquals(result.dynDns, "123.a-n-t.ru")
        Assert.assertEquals(result.state.balance, 123.45, 0.1)
        Assert.assertEquals(result.state.downloaded, 421)
        Assert.assertEquals(result.state.status, true)
        Assert.assertEquals(result.state.credit, 200)
        Assert.assertEquals(result.state.smsInfo, true)
        Assert.assertEquals(result.tariff.name, "VX_SOMETHING")
        Assert.assertEquals(result.tariff.downloadSpeed, 74)
        Assert.assertEquals(result.tariff.uploadSpeed, 47)
        Assert.assertEquals(result.tariff.price, 789.01, 0.1)

        Assert.assertEquals(result.pricePerDay, 26.3, 0.1)
        Assert.assertEquals(result.daysLeft, 12)
    }
}