package com.assistant.ant.solidlsnake.antassistant.presentation.worker

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.assistant.ant.solidlsnake.antassistant.data.local.ILocalService
import com.assistant.ant.solidlsnake.antassistant.data.remote.IRemoteService
import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import com.assistant.ant.solidlsnake.antassistant.domain.Mapper
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import java.util.concurrent.TimeUnit

class UpdateDataWorker(context: Context, params: WorkerParameters) : Worker(context, params), KoinComponent {

    private val remoteService: IRemoteService by inject()
    private val localService: ILocalService by inject()
    private val mapper: Mapper<UserDataResponse, UserData> by inject(named("REMOTE"))

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

    private fun restart() {
        val request = OneTimeWorkRequest.Builder(UpdateDataWorker::class.java)
                .setInitialDelay(24, TimeUnit.HOURS)
                .build()

        WorkManager.getInstance().enqueue(request)
    }

    override fun doWork(): Result {
        restart()

        val result = getData()
        if (result is Result.Success) {
            // todo: уведомление
        }
        return result
    }
}