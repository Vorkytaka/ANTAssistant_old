package com.assistant.ant.solidlsnake.antassistant.mvp

abstract class Presenter<V : MvpView> {
    protected var view: V? = null
        private set

    fun attachView(view: V) {
        this.view = view
        doOnAttach()
    }

    fun detachView() {
        this.view = null
    }

    open fun doOnAttach() {
    }

    open fun onDestroy() {
    }

}
