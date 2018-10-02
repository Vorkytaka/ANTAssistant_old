package com.assistant.ant.solidlsnake.antassistant.data.parser

import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import org.jsoup.Jsoup
import java.util.regex.Pattern

object Parser {
    private const val SUCCESS_TITLE = "Информация о счете"
    private const val STATUS_ACTIVE = "Активна"

    suspend fun isLogged(body: String): Boolean {
        val doc = Jsoup.parse(body)
        return doc.title() == SUCCESS_TITLE
    }

    suspend fun userData(body: String): UserData {
        val doc = Jsoup.parse(body)

        val balance = doc.select("td.num").first().ownText().replace(" руб.", "").toDouble()

        val tables = doc.select("td.tables")
        for (i in 0 until tables.size step 3) {
            when (tables[i].ownText()) {
                "Код плательщика" -> {
                    val userId = tables[i + 1].text()
                }
                "Тариф" -> {
                    val tariffStr = tables[i + 1].text()

                    val name = tariffStr.substring(0, tariffStr.indexOf(':'))

                    var pattern = Pattern.compile("\\d{3,}")
                    var matcher = pattern.matcher(tariffStr)

                    if (matcher.find()) {
                        val price = Math.round(matcher.group(0).toDouble() / 10.0) * 10
                    }

                    pattern = Pattern.compile("\\d+/\\d+")
                    matcher = pattern.matcher(tariffStr)

                    if (matcher.find()) {
                        val speeds = matcher.group().split("/")

                        val downloadSpeed = speeds[0]
                        val uploadSpeed = speeds[1]
                    }
                }
                "Кредит доверия, руб" -> {
                    val credit = tables[i + 1].text().toInt()
                }
                "Статус учетной записи" -> {
                    val statusStr = tables[i + 1].text()
                    val status = statusStr == STATUS_ACTIVE
                }
            }
        }

        TODO()
    }
}