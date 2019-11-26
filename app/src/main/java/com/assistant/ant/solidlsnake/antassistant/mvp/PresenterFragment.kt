package com.assistant.ant.solidlsnake.antassistant.mvp

import android.support.v4.app.Fragment

abstract class PresenterFragment : Fragment(), PresenterStoreOwner {
    private var presenterStore: PresenterStore? = null

    override fun onDestroy() {
        super.onDestroy()

        val isChangingConfigurations = activity?.isChangingConfigurations ?: false
        if (isChangingConfigurations) {
            presenterStore?.clear()
        }
    }

    override fun getPresenterStore(): PresenterStore {
        if (presenterStore == null) {
            presenterStore = PresenterStore()
        }
        return presenterStore!!
    }
}