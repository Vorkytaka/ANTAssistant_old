package com.assistant.ant.solidlsnake.antassistant.presentation.presenter

import com.assistant.ant.solidlsnake.antassistant.presentation.view.BaseView

abstract class BasePresenter<V : BaseView> {
    protected var _view: V? = null

    fun attachView(view: V) {
        _view = view
        doOnAttach()
    }

    fun detachView() {
        _view = null
    }

    open fun doOnAttach() {}
}