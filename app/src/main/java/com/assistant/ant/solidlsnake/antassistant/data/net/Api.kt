package com.assistant.ant.solidlsnake.antassistant.data.net

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object Api {
    private const val BASE_URL = "http://cabinet.a-n-t.ru/cabinet.php"

    private const val PARAM_ACTION = "action"
    private const val ACTION_INFO = "info"
    private const val PARAM_USERNAME = "user_name"
    private const val PARAM_PASSWORD = "user_pass"

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
    suspend fun info(login: String, password: String): String {
        return suspendCancellableCoroutine { coroutine ->
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
            call.enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    response.body()?.use {
                        val body = it.string()
                        if (body == null) {
                            coroutine.resumeWithException(NullPointerException("There's no body, buddy"))
                        } else {
                            coroutine.resume(body)
                        }
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    if (coroutine.isCancelled) return
                    coroutine.resumeWithException(e)
                }
            })

            coroutine.invokeOnCompletion() {
                if (coroutine.isCancelled)
                    try {
                        call.cancel()
                    } catch (ex: Throwable) {
                        // Ignore cancel exception
                    }
            }
        }
    }
}