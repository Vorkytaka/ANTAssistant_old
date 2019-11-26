package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.mvp.Presenter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.BaseView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V : BaseView> : Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    protected var view: V? = null
        private set

    fun attachView(view: V) {
        this.view = view
        doOnAttach()
    }

    fun detachView() {
        view = null
    }

    open fun doOnAttach() {}

    override fun onCleared() = Unit
}