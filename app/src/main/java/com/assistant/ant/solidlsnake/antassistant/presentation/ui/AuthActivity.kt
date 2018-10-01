package com.assistant.ant.solidlsnake.antassistant.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.assistant.ant.solidlsnake.antassistant.R

class AuthActivity : BaseActivity() {
    companion object {
        @JvmStatic
        fun getIntent(activity: Activity): Intent {
            return Intent(activity, AuthActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}
