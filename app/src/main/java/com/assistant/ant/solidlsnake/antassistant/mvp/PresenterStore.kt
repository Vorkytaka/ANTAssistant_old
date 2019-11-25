package com.assistant.ant.solidlsnake.antassistant.mvp

class PresenterStore {
    private val map = hashMapOf<String, Presenter>()

    fun put(key: String, presenter: Presenter) {
        val oldPresenter = map.put(key, presenter)
        oldPresenter?.onCleared()
    }

    fun get(key: String): Presenter? = map[key]

    fun clear() {
        map.values.forEach(Presenter::onCleared)
        map.clear()
    }
}