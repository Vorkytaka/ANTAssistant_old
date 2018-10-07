package com.assistant.ant.solidlsnake.antassistant.domain.interactor

/**
 * P - Параметры
 * R - Ответ
 */
abstract class UseCase<P, R> {
    protected var params: P? = null

    fun params(params: P): UseCase<P, R> {
        this.params = params
        return this
    }

    abstract suspend fun execute(success: (R) -> Unit, error: (Throwable) -> Unit)
}