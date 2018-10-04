package com.assistant.ant.solidlsnake.antassistant.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.assistant.ant.solidlsnake.antassistant.R
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import com.assistant.ant.solidlsnake.antassistant.presentation.presenter.MainPresenter
import com.assistant.ant.solidlsnake.antassistant.presentation.view.MainView

class MainActivity : BaseActivity(), MainView {
    companion object {
        fun getIntent(activity: Activity): Intent {
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            return intent
        }
    }

    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun setProgress(progress: Boolean) {

    }

    override fun showUserData(data: UserData) {

    }
}
