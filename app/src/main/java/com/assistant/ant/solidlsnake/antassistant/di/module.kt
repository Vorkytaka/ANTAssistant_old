package com.assistant.ant.solidlsnake.antassistant.di

import com.assistant.ant.solidlsnake.antassistant.data.local.ILocalService
import com.assistant.ant.solidlsnake.antassistant.data.local.LocalServiceImpl
import com.assistant.ant.solidlsnake.antassistant.data.local.account.AccountHolderImpl
import com.assistant.ant.solidlsnake.antassistant.data.local.account.IAccountHolder
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
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.AuthPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.LaunchPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.SettingsPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity.AuthActivity
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity.LaunchActivity
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity.MainActivity
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.fragment.SettingsFragment
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    scope(named<AuthActivity>()) {
        scoped { AuthPresenter(get()) }
    }

    scope(named<MainActivity>()) {
        scoped { MainPresenter(get(), get(), get()) }
    }

    scope(named<LaunchActivity>()) {
        scoped { LaunchPresenter(get()) }
    }

    scope(named<SettingsFragment>()) {
        scoped { SettingsPresenter(get(), get(), get()) }
    }
}

val dataModule = module {
    single { Parser() }
    single { Api() }
    single<IAccountHolder> { AccountHolderImpl(androidContext()) }

    single<Mapper<UserDataResponse, UserData>>(named("REMOTE")) { UserDataResponse::toUserData }
    single<Mapper<UserDataModel, UserData>>(named("LOCAL")) { UserDataModel::toUserData }

    single<ILocalService> { LocalServiceImpl(get()) }
    single<IRemoteService> { RemoteServiceImpl(get(), get()) }
}

val domainModule = module {
    factory { Login(get()) }
    factory { GetUserData(get()) }
    factory { IsLogged(get()) }
    factory { CanSetCredit(get()) }
    factory { MaxAvailableCredit(get()) }
    factory { Logout(get()) }

    factory { SaveSettings(get()) }
    factory { GetSettings(get()) }

    single<IRepository> { RepositoryImpl(get(), get(), get(named("REMOTE")), get(named("LOCAL"))) }
    single<ISettingsRepository> { SettingsRepositoryImpl() }
}