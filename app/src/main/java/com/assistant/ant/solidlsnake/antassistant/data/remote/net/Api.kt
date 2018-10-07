package com.assistant.ant.solidlsnake.antassistant.data.remote.net

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import okhttp3.*
import java.io.IOException

class Api {
    companion object {
        private const val BASE_URL = "http://cabinet.a-n-t.ru/cabinet.php"

        private const val PARAM_ACTION = "action"
        private const val ACTION_INFO = "info"
        private const val PARAM_USERNAME = "user_name"
        private const val PARAM_PASSWORD = "user_pass"
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .build()
    }

    /**
     * Пытается получить данные со страницы [http://cabinet.a-n-t.ru/cabinet.php?action=info]
     * используя переданные логин и пароль.
     *
     * Возвращает полную верстку страницы
     *
     * @param login имя пользователя
     * @param password пароль пользователя
     *
     * @return полную верстку страницы в виде строки
     */
    fun info(login: String, password: String): Deferred<ResponseBody> {
        val deferred = CompletableDeferred<ResponseBody>()

        val params = FormBody.Builder()
                .add(PARAM_ACTION, ACTION_INFO)
                .add(PARAM_USERNAME, login)
                .add(PARAM_PASSWORD, password)
                .build()

        val request = Request.Builder()
                .url(BASE_URL)
                .post(params)
                .build()

        val call = client.newCall(request)

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                call.cancel()
            }
        }

        call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    deferred.complete(response.body()!!)
                } else {
                    deferred.cancel(IllegalStateException(""))
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                deferred.cancel(e)
            }
        })

        return deferred
    }
}