package com.assistant.ant.solidlsnake.antassistant.di

import android.annotation.SuppressLint
import android.content.Context
import com.assistant.ant.solidlsnake.antassistant.data.local.ILocalService
import com.assistant.ant.solidlsnake.antassistant.data.local.LocalServiceImpl
import com.assistant.ant.solidlsnake.antassistant.data.local.account.AccountHolderImpl
import com.assistant.ant.solidlsnake.antassistant.data.local.account.IAccountHolder
import com.assistant.ant.solidlsnake.antassistant.data.local.alarm.IAlarmUtils
import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.data.mapper.toUserData
import com.assistant.ant.solidlsnake.antassistant.data.remote.IRemoteService
import com.assistant.ant.solidlsnake.antassistant.data.remote.RemoteServiceImpl
import com.assistant.ant.solidlsnake.antassistant.data.remote.net.Api
import com.assistant.ant.solidlsnake.antassistant.data.remote.parser.Parser
import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import com.assistant.ant.solidlsnake.antassistant.data.repository.RepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.data.repository.SettingsRepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.domain.Mapper
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.*
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.domain.repository.ISettingsRepository
import com.assistant.ant.solidlsnake.antassistant.presentation.broadcast.AlarmUtilsImpl
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.factory.PresenterFactory

val applicationModule: AppModule = AppComponent

interface AppModule {
    val presenterFactory: PresenterFactory

    val parser: Parser
    val api: Api

    val accountHolder: IAccountHolder

    val remoteMapper: Mapper<UserDataResponse, UserData>
    val localMapper: Mapper<UserDataModel, UserData>

    val localService: ILocalService
    val remoteService: IRemoteService

    val alarmUtils: IAlarmUtils

    val loginUseCase: Login
    val getUserDataUseCase: GetUserData
    val isLoggedUseCase: IsLogged
    val canSetCreditUseCase: CanSetCredit
    val maxAvailableCreditUseCase: MaxAvailableCredit
    val logoutUseCase: Logout
    val saveSettingsUseCase: SaveSettings
    val getSettingsUseCase: GetSettings

    val repository: IRepository
    val settingsRepository: ISettingsRepository
}

@SuppressLint("StaticFieldLeak")
object AppComponent : AppModule {
    override val presenterFactory: PresenterFactory
        get() = PresenterFactory(loginUseCase, isLoggedUseCase, getUserDataUseCase, maxAvailableCreditUseCase, canSetCreditUseCase, logoutUseCase, getSettingsUseCase, saveSettingsUseCase)

    private lateinit var context: Context

    override val parser: Parser = Parser()
    override val api: Api = Api()

    override val accountHolder: IAccountHolder by lazy { AccountHolderImpl(context) }

    override val remoteMapper: Mapper<UserDataResponse, UserData> = UserDataResponse::toUserData
    override val localMapper: Mapper<UserDataModel, UserData> = UserDataModel::toUserData

    override val localService: ILocalService by lazy { LocalServiceImpl(accountHolder) }
    override val remoteService: IRemoteService = RemoteServiceImpl(api, parser)

    override val alarmUtils: IAlarmUtils by lazy { AlarmUtilsImpl(context) }

    override val loginUseCase: Login
        get() = Login(repository)
    override val getUserDataUseCase: GetUserData
        get() = GetUserData(repository)
    override val isLoggedUseCase: IsLogged
        get() = IsLogged(repository)
    override val canSetCreditUseCase: CanSetCredit
        get() = CanSetCredit(repository)
    override val maxAvailableCreditUseCase: MaxAvailableCredit
        get() = MaxAvailableCredit(repository)
    override val logoutUseCase: Logout
        get() = Logout(repository)
    override val saveSettingsUseCase: SaveSettings
        get() = SaveSettings(settingsRepository)
    override val getSettingsUseCase: GetSettings
        get() = GetSettings(settingsRepository)

    override val repository: IRepository by lazy { RepositoryImpl(remoteService, localService, remoteMapper, localMapper) }
    override val settingsRepository: ISettingsRepository = SettingsRepositoryImpl()

    fun init(context: Context) {
        this.context = context
    }
}