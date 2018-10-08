package com.assistant.ant.solidlsnake.antassistant.di

import com.assistant.ant.solidlsnake.antassistant.data.local.ILocalService
import com.assistant.ant.solidlsnake.antassistant.data.local.LocalServiceImpl
import com.assistant.ant.solidlsnake.antassistant.data.local.account.AccountHolderImpl
import com.assistant.ant.solidlsnake.antassistant.data.local.account.IAccountHolder
import com.assistant.ant.solidlsnake.antassistant.data.remote.IRemoteService
import com.assistant.ant.solidlsnake.antassistant.data.remote.RemoteServiceImpl
import com.assistant.ant.solidlsnake.antassistant.data.remote.net.Api
import com.assistant.ant.solidlsnake.antassistant.data.remote.parser.Parser
import com.assistant.ant.solidlsnake.antassistant.data.repository.RepositoryImpl
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.Auth
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.GetUserData
import com.assistant.ant.solidlsnake.antassistant.domain.interactor.IsLogged
import com.assistant.ant.solidlsnake.antassistant.domain.repository.IRepository
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.AuthPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.LaunchPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { Parser() }
    single { Api() }
    single<IAccountHolder> { AccountHolderImpl(androidContext()) }

    single<ILocalService> { LocalServiceImpl(get()) }
    single<IRemoteService> { RemoteServiceImpl(get(), get()) }

    single<IRepository> { RepositoryImpl(get(), get()) }

    factory { Auth(get()) }
    factory { GetUserData(get()) }
    factory { IsLogged(get()) }

    factory { AuthPresenter(get()) }
    factory { MainPresenter(get()) }
    factory { LaunchPresenter(get()) }
}