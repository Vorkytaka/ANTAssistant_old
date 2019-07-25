package com.assistant.ant.solidlsnake.antassistant.data.remote.net

import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import okhttp3.*
import java.io.IOException

class Api {
    companion object {
        private const val BASE_URL = "http://cabinet.a-n-t.ru/cabinet.php"

        private const val PARAM_ACTION = "action"
        private const val PARAM_USERNAME = "user_name"
        private const val PARAM_PASSWORD = "user_pass"
        private const val PARAM_CREDIT = "credit"

        private const val ACTION_INFO = "info"
        private const val ACTION_CREDIT = "changecredit2"
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .build()
    }

    /**
     * Пытается получить данные со страницы [http://cabinet.a-n-t.ru/cabinet.php?action=info]
     * используя переданные логин и пароль.
     *
     * @param login имя пользователя
     * @param password пароль пользователя
     *
     * @return ответ сервера на запрос
     */
    fun info(login: String, password: String): Deferred<ResponseBody> {
        val params = FormBody.Builder()
                .add(PARAM_ACTION, ACTION_INFO)
                .add(PARAM_USERNAME, login)
                .add(PARAM_PASSWORD, password)
                .build()

        val request = Request.Builder()
                .url(BASE_URL)
                .post(params)
                .build()

        return client.newCall(request).toDeferred()
    }

    /**
     * Выставление кредита доверия.
     *
     * @param   login имя пользователя
     * @param   password пароль пользователя
     * @param   creditValue желаемый кредит доверия.
     *          Пока поддерживаются только значения V_100, V_200, V_300.
     *          В остальных случаях будет выкидываться IllegalStateException
     */
    fun credit(login: String, password: String, creditValue: CreditValue): Deferred<ResponseBody> {
        val credit = when (creditValue) {
            CreditValue.V_100 -> 100
            CreditValue.V_200 -> 200
            CreditValue.V_300 -> 300
            else -> throw IllegalStateException()
        }

        val params = FormBody.Builder()
                .add(PARAM_ACTION, ACTION_CREDIT)
                .add(PARAM_USERNAME, login)
                .add(PARAM_PASSWORD, password)
                .add(PARAM_CREDIT, credit.toString())
                .build()

        val request = Request.Builder()
                .url(BASE_URL)
                .post(params)
                .build()

        return client.newCall(request).toDeferred()
    }

    /**
     * Вспомогательаня функция для переделки {@see Call}
     * на {@link Deferred}
     */
    private fun Call.toDeferred(): Deferred<ResponseBody> {
        val deferred = CompletableDeferred<ResponseBody>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                this.cancel()
            }
        }

        this.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    deferred.complete(body)
                } else {
                    deferred.cancel()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                deferred.cancel()
            }
        })

        return deferred
    }

    private fun Call.toReceiveChannel(): ReceiveChannel<ResponseBody> {
        val channel = Channel<ResponseBody>(1)

        channel.invokeOnClose {
            if (channel.isClosedForSend) {
                this.cancel()
            }
        }

        this.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    channel.offer(body)
                } else {
                    channel.close()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                channel.close()
            }
        })

        return channel
    }
}