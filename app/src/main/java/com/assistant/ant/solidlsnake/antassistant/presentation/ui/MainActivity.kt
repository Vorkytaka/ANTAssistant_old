package com.assistant.ant.solidlsnake.antassistant.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.assistant.ant.solidlsnake.antassistant.R

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(activity: Activity): Intent {
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
