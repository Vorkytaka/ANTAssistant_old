package com.assistant.ant.solidlsnake.antassistant.data.net

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import org.jsoup.Jsoup
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object Api {
    private const val BASE_URL = "http://cabinet.a-n-t.ru/cabinet.php"

    private const val PARAM_ACTION = "action"
    private const val ACTION_INFO = "info"
    private const val PARAM_USERNAME = "user_name"
    private const val PARAM_PASSWORD = "user_pass"

    private const val SUCCESS_TITLE = "Информация о счете"

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .build()
    }

    suspend fun auth(login: String, password: String): Boolean {
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
                    val body = response.body()?.string()

                    if (body == null) {
                        coroutine.resumeWithException(NullPointerException("There's no body, buddy"))
                    } else {
                        val document = Jsoup.parse(body)
                        coroutine.resume(document.title() == SUCCESS_TITLE)
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