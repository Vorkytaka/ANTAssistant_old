package com.assistant.ant.solidlsnake.antassistant.domain.interactor.common

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel

abstract class ChannelUseCase<P, R>(
        private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO,
        private val foregroundDispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    protected var params: P? = null
    private var job: Job = Job()
    private var channel: ReceiveChannel<R>? = null

    fun params(params: P): ChannelUseCase<P, R> {
        this.params = params
        return this
    }

    protected abstract suspend fun getChannel(): ReceiveChannel<R>

    fun subscribe(action: (R) -> Unit) {
        cancel()
        job = Job()

        CoroutineScope(foregroundDispatcher + job).launch {
            val channel = getChannel()
            this@ChannelUseCase.channel = channel
            for (item in channel) {
                action(item)
            }
        }
    }

    fun cancel() {
        job.cancelChildren()
        job.cancel()
        if (channel != null && !channel!!.isClosedForReceive) {
            channel!!.cancel()
        }
    }
}