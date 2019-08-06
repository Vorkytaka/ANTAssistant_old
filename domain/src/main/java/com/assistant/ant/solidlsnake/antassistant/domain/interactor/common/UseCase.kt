package com.assistant.ant.solidlsnake.antassistant.domain.interactor.common

import kotlinx.coroutines.*


/**
 * @param   <P> (Parameters) тип данных,
 *          передаваемых в виде параметра
 *
 * @param   <R> (Response) тип данных,
 *          возвращаемый после выполнения
 */
abstract class UseCase<P, R>(
        private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO,
        private val foregroundDispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    protected var params: P? = null
    private var job: Job = Job()

    fun params(params: P): UseCase<P, R> {
        this.params = params
        return this
    }

    protected abstract suspend fun doOnBackground(): R

    fun execute(action: (R) -> Unit) {
        cancel()
        job = Job()

        CoroutineScope(foregroundDispatcher + job).launch {
            val result = withContext(backgroundDispatcher) { doOnBackground() }
            action(result)
        }
    }

    fun cancel() {
        job.cancelChildren()
        job.cancel()
    }
}