package com.assistant.ant.solidlsnake.antassistant.di

import com.assistant.ant.solidlsnake.antassistant.data.local.ILocalService
import com.assistant.ant.solidlsnake.antassistant.data.local.LocalServiceImpl
import com.assistant.ant.solidlsnake.antassistant.data.local.account.AccountHolderImpl
import com.assistant.ant.solidlsnake.antassistant.data.local.account.IAccountHolder
import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.data.mapper.UserDataModelMapper
import com.assistant.ant.solidlsnake.antassistant.data.mapper.UserDataResponseMapper
import com.assistant.ant.solidlsnake.antassistant.data.remote.IRemoteService
import com.assistant.ant.solidlsnake.antassistant.data.remote.RemoteServiceImpl
import com.assistant.ant.solidlsnake.antassistant.data.remote.net.Api
import com.assistant.ant.solidlsnake.antassistant.data.remote.parser.Parser
import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import com.assistant.ant.solidlsnake.antassistant.data.repository.RepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.domain.Mapper
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.*
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.AuthPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.LaunchPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.SettingsPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    factory { Login(get()) }
    factory { GetUserData(get()) }
    factory { IsLogged(get()) }
    factory { CanSetCredit(get()) }
    factory { MaxAvailableCredit(get()) }
    factory { Logout(get()) }

    factory { AuthPresenter(get()) }
    factory { MainPresenter(get(), get(), get()) }
    factory { LaunchPresenter(get()) }
    factory { SettingsPresenter(get()) }
}

val dataModule = module {
    single { Parser() }
    single { Api() }
    single<IAccountHolder> { AccountHolderImpl(androidContext()) }

    single<Mapper<UserDataResponse, UserData>>("REMOTE") { UserDataResponseMapper() }
    single<Mapper<UserDataModel, UserData>>("LOCAL") { UserDataModelMapper() }

    single<ILocalService> { LocalServiceImpl(get()) }
    single<IRemoteService> { RemoteServiceImpl(get(), get()) }
}

val domainModule = module {
    single<IRepository> { RepositoryImpl(get(), get(), get("REMOTE"), get("LOCAL")) }
}