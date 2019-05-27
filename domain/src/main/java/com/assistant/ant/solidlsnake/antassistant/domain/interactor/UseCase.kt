package com.assistant.ant.solidlsnake.antassistant.domain.interactor

import kotlinx.coroutines.*

/**
 * @param   <P> (Parameters) тип данных,
 *          передаваемых в виде параметра
 *
 * @param   <R> (Response) тип данных,
 *          возвращаемый после выполнения
 */
abstract class UseCase<P, R> {
    protected var params: P? = null

    private var job = Job()

    fun params(params: P): UseCase<P, R> {
        this.params = params
        return this
    }

    abstract suspend fun useCaseAction(): R

    fun execute(action: (R) -> Unit) {
        if (job.isActive) {
            cancel()
            job = Job()
        }

        CoroutineScope(Dispatchers.Main + job).launch {
            val result = withContext(Dispatchers.IO) {
                useCaseAction()
            }
            action(result)
        }
    }

    fun cancel() {
        job.cancelChildren()
        job.cancel()
    }
}