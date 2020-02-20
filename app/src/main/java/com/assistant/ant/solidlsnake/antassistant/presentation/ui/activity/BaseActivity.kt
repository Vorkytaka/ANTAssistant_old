package com.assistant.ant.solidlsnake.antassistant.presentation.ui.activity

import com.assistant.ant.solidlsnake.antassistant.mvp.MvpView
import com.assistant.ant.solidlsnake.antassistant.mvp.Presenter
import com.assistant.ant.solidlsnake.antassistant.mvp.PresenterActivity

abstract class BaseActivity<V : MvpView, P : Presenter<V>> : PresenterActivity<V, P>()