package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.mvp.Presenter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.BaseView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V : BaseView> : Presenter<V>(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}