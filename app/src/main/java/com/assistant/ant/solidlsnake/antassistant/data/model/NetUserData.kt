package com.assistant.ant.solidlsnake.antassistant.data.model

class NetUserData {
    var accountName: String = ""
    var userId: String = ""

    var state__balance: Double = 0.0
    var state__downloaded: Int = 0
    var status: Boolean = false
    var credit: Int = 0

    var tariff_name: String = ""
    var tariff_downloadSpeed: Int = 0
    var tariff_uploadSpeed: Int = 0
    var tariff_price: Double = 0.0
}