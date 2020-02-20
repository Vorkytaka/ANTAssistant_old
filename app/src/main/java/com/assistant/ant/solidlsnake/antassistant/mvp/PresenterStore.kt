package com.assistant.ant.solidlsnake.antassistant.mvp

class PresenterStore {
    private val map = hashMapOf<String, Presenter<*>>()

    fun put(key: String, presenter: Presenter<*>) {
        val oldPresenter = map.put(key, presenter)
        oldPresenter?.onDestroy()
    }

    fun <V : MvpView> get(key: String): Presenter<V>? = map[key] as? Presenter<V>

    fun clear() {
        map.values.forEach(Presenter<*>::onDestroy)
        map.clear()
    }
}