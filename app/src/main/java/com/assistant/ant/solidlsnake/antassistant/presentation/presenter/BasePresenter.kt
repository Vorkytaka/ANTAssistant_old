package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.presentation.view.BaseView

abstract class BasePresenter<V : BaseView> {
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
}