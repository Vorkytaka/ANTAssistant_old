package com.assistant.ant.solidlsnake.antassistant.mvp

import android.support.v7.app.AppCompatActivity

abstract class PresenterActivity<V : MvpView, P : Presenter<V>> : AppCompatActivity(), PresenterStoreOwner, MvpView {
    abstract val presenterClazz: Class<P>

    protected open val presenterFactory: PresenterProvider.Factory? = null

    protected val presenter: P by lazy { getPresenterProvider(presenterFactory)[presenterClazz] }

    private var presenterStore: PresenterStore? = null

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenterStore
    }

    override fun onDestroy() {
        super.onDestroy()

        if (!isChangingConfigurations) {
            presenterStore?.clear()
        }
    }

    override fun getPresenterStore(): PresenterStore {
        if (presenterStore == null) {

            val lastPresenterStore = lastCustomNonConfigurationInstance as? PresenterStore
            if (lastPresenterStore != null) {
                presenterStore = lastPresenterStore
            }

            if (presenterStore == null) {
                presenterStore = PresenterStore()
            }
        }

        return presenterStore!!
    }
}