package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import com.assistant.ant.solidlsnake.antassistant.mvp.Presenter
import com.assistant.ant.solidlsnake.antassistant.mvp.PresenterProvider
import com.assistant.ant.solidlsnake.antassistant.mvp.getPresenterProvider

abstract class BasePresenterActivity<P : Presenter> : BaseActivity() {
    abstract val presenterClazz: Class<P>
    protected open val presenterFactory: PresenterProvider.Factory = PresenterProvider.NewInstanceFactory()

    protected val presenter: P by lazy { getPresenterProvider(presenterFactory)[presenterClazz] }
}