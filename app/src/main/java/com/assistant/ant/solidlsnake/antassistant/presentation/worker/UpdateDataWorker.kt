package com.assistant.ant.solidlsnake.antassistant.presentation.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.assistant.ant.solidlsnake.antassistant.data.local.ILocalService
import com.assistant.ant.solidlsnake.antassistant.data.remote.IRemoteService
import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import com.assistant.ant.solidlsnake.antassistant.domain.Mapper
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import kotlinx.coroutines.runBlocking
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class UpdateDataWorker(context: Context, params: WorkerParameters) : Worker(context, params), KoinComponent {

    private val remoteService: IRemoteService by inject()
    private val localService: ILocalService by inject()
    private val mapper: Mapper<UserDataResponse, UserData> by inject("REMOTE")

    private fun getData() = runBlocking {
        val credentials = localService.getCredentials()

        if (credentials != null) {
            val remoteData = remoteService.getUserData(credentials)

            if (remoteData != null) {
                val data = mapper(remoteData)
                localService.saveUserData(data)
                return@runBlocking Result.success()
            }

            return@runBlocking Result.retry()
        }

        return@runBlocking Result.failure()
    }

    override fun doWork(): Result {
        // todo: перезапуск этого воркера через 24 часа
        return getData()
    }
}