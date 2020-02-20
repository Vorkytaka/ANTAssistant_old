package com.assistant.ant.solidlsnake.antassistant.mvp

fun PresenterActivity.getPresenterProvider(factory: PresenterProvider.Factory = PresenterProvider.NewInstanceFactory()): PresenterProvider {
    return PresenterProvider(this, factory)
}

fun PresenterFragment.getPresenterProvider(factory: PresenterProvider.Factory = PresenterProvider.NewInstanceFactory()): PresenterProvider {
    return PresenterProvider(this, factory)
}

class PresenterProvider(
        private val presenterStore: PresenterStore,
        private val factory: Factory
) {
    constructor(storeOwner: PresenterStoreOwner, factory: Factory) : this(storeOwner.getPresenterStore(), factory)

    operator fun <V : MvpView, P : Presenter<V>> get(modelClass: Class<P>): P {
        val name = modelClass.canonicalName ?: throw Exception()
        return get("$DEFAULT_KEY:$name", modelClass)
    }

    @Suppress("UNCHECKED_CAST")
    fun <V : MvpView, P : Presenter<V>> get(key: String, modelClass: Class<P>): P {
        var presenter = presenterStore.get<V>(key)

        if (presenter != null && modelClass.isInstance(presenter)) {
            return presenter as P
        }

        presenter = factory.create(modelClass)
        presenterStore.put(key, presenter)
        return presenter
    }

    companion object {
        private const val DEFAULT_KEY: String = "PresenterProvider.Default"
    }

    interface Factory {
        fun <V : MvpView, P : Presenter<V>> create(modelClass: Class<P>): P
    }

    class NewInstanceFactory : Factory {
        override fun <V : MvpView, P : Presenter<V>> create(modelClass: Class<P>): P {
            return modelClass.newInstance()
        }
    }
}