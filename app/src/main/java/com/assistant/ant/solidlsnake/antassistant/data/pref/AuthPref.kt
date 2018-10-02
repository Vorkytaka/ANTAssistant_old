package com.assistant.ant.solidlsnake.antassistant.data.pref

import com.chibatching.kotpref.KotprefModel

object AuthPref : KotprefModel() {
    val isLogged: Boolean
        get() = login.isNotEmpty() && password.isNotEmpty()

    var login by stringPref(default = "")
    var password by stringPref(default = "")
}