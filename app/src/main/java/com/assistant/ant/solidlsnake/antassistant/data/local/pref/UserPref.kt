package com.assistant.ant.solidlsnake.antassistant.data.local.pref

import com.chibatching.kotpref.KotprefModel

object UserPref : KotprefModel() {
    var accountName by stringPref(default = "")
    var userId by stringPref(default = "")

    var state_balance by floatPref(default = 0f)
    var state_downloaded by intPref(default = 0)
    var state_status by booleanPref(default = false)
    var state_credit by intPref(default = 0)

    var tariff_name by stringPref(default = "")
    var tariff_downloadSpeed by intPref(default = 0)
    var tariff_uploadSpeed by intPref(default = 0)
    var tariff_price by floatPref(default = 0f)
}