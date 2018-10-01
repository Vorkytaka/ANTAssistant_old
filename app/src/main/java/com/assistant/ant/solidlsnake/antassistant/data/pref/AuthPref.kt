package com.assistant.ant.solidlsnake.antassistant.data.pref

import com.chibatching.kotpref.KotprefModel

object AuthPref : KotprefModel() {
    var isLogged by booleanPref(default = false)
}