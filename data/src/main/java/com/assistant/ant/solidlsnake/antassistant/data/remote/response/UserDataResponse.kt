package com.assistant.ant.solidlsnake.antassistant.data.remote.response

class UserDataResponse {
    var accountName: String = ""
    var userId: String = ""
    var dynDns: String = ""

    var state_balance: Double = 0.0
    var state_downloaded: Int = 0
    var state_status: Boolean = false
    var state_credit: Int = 0
    var state_smsInfo: Boolean = false

    var tariff_name: String = ""
    var tariff_downloadSpeed: Int = 0
    var tariff_uploadSpeed: Int = 0
    var tariff_price: Double = 0.0
}