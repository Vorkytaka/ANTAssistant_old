package com.assistant.ant.solidlsnake.antassistant.data.pref

import com.chibatching.kotpref.KotprefModel

object UserPref : KotprefModel() {
    var accountName by stringPref(default = "")
    var userId by stringPref(default = "")

    var state__balance by floatPref(default = 0f)
    var state__downloaded by intPref(default = 0)
    var status by booleanPref(default = false)
    var credit by intPref(default = 0)

    var tariff_name by stringPref(default = "")
    var tariff_downloadSpeed by intPref(default = 0)
    var tariff_uploadSpeed by intPref(default = 0)
    var tariff_price by floatPref(default = 0f)
}