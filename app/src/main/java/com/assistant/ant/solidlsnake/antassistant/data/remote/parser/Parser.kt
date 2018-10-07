package com.assistant.ant.solidlsnake.antassistant.data.remote.parser

import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.util.regex.Pattern

object Parser {
    private const val SUCCESS_TITLE = "Информация о счете"
    private const val STATUS_ACTIVE = "Активна"

    suspend fun isLogged(body: String): Boolean = withContext(Dispatchers.IO) {
        val doc = Jsoup.parse(body)
        doc.title() == SUCCESS_TITLE
    }

    suspend fun userData(body: String): UserDataResponse = withContext(Dispatchers.IO) {
        val data = UserDataResponse()

        val doc = Jsoup.parse(body)

        val balance = doc.select("td.num").first().ownText().replace(" руб.", "").toDouble()
        data.state_balance = balance

        val tables = doc.select("td.tables")
        for (i in 0 until tables.size step 3) {
            GlobalScope.launch {
                when (tables[i].ownText()) {
                    "Код плательщика" -> {
                        val userId = tables[i + 1].text()
                        data.userId = userId
                    }
                    "Тариф" -> {
                        val tariffStr = tables[i + 1].text()

                        val name = tariffStr.substring(0, tariffStr.indexOf(':'))
                        data.tariff_name = name

                        var pattern = Pattern.compile("\\d{3,}")
                        var matcher = pattern.matcher(tariffStr)

                        if (matcher.find()) {
                            val price = Math.round(matcher.group(0).toDouble() / 10.0) * 10.0
                            data.tariff_price = price
                        }

                        pattern = Pattern.compile("\\d+/\\d+")
                        matcher = pattern.matcher(tariffStr)

                        if (matcher.find()) {
                            val speeds = matcher.group().split("/")

                            val downloadSpeed = speeds[0].toInt()
                            val uploadSpeed = speeds[1].toInt()

                            data.tariff_downloadSpeed = downloadSpeed
                            data.tariff_uploadSpeed = uploadSpeed
                        }
                    }
                    "Кредит доверия, руб" -> {
                        val credit = tables[i + 1].text().toInt()
                        data.state_credit = credit
                    }
                    "Статус учетной записи" -> {
                        val statusStr = tables[i + 1].text()
                        val status = statusStr == STATUS_ACTIVE
                        data.state_status = status
                    }
                    "Скачано за текущий месяц" -> {
                        val downloaded = tables[i + 1].text().replace(" ( Мб. )", "").toInt()
                        data.state_downloaded = downloaded
                    }
                    "Учетная запись" -> {
                        val accountName = tables[i + 1].text()
                        data.accountName = accountName
                    }
                }
            }
        }

        data
    }
}