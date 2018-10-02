package com.assistant.ant.solidlsnake.antassistant.data.parser

import org.jsoup.Jsoup

object Parser {
    private const val SUCCESS_TITLE = "Информация о счете"

    suspend fun isLogged(body: String): Boolean {
        val doc = Jsoup.parse(body)
        return doc.title() == SUCCESS_TITLE
    }
}