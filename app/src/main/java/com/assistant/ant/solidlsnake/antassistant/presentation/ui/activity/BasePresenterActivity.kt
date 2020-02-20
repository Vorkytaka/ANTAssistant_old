package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import com.assistant.ant.solidlsnake.antassistant.mvp.MvpView
import com.assistant.ant.solidlsnake.antassistant.mvp.Presenter
import com.assistant.ant.solidlsnake.antassistant.mvp.PresenterProvider
import com.assistant.ant.solidlsnake.antassistant.mvp.getPresenterProvider

abstract class BasePresenterActivity<V : MvpView, P : Presenter<V>> : BaseActivity() {
    abstract val presenterClazz: Class<P>
    protected open val presenterFactory: PresenterProvider.Factory = PresenterProvider.NewInstanceFactory()

    protected val presenter: P by lazy { getPresenterProvider(presenterFactory)[presenterClazz] }
}