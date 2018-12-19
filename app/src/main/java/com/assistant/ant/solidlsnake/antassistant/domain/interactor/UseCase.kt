package com.assistant.ant.solidlsnake.antassistant.domain.interactor

/**
 * @param   <P> (Parameters) тип данных,
 *          передаваемых в виде параметра
 *
 * @param   <R> (Response) тип данных,
 *          возвращаемый после выполнения
 */
abstract class UseCase<P, R> {
    protected var params: P? = null

    fun params(params: P): UseCase<P, R> {
        this.params = params
        return this
    }

    abstract suspend fun execute(action: (R) -> Unit)
}