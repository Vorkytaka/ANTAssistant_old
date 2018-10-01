package com.assistant.ant.solidlsnake.antassistant

fun CharSequence?.orEmpty(): String = this?.toString() ?: ""