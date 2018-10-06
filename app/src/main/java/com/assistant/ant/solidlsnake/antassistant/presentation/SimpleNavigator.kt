package com.assistant.ant.solidlsnake.antassistant.presentation

import android.app.Activity
import android.content.Intent
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.AuthActivity
import com.assistant.ant.solidlsnake.antassistant.presentation.ui.MainActivity

object SimpleNavigator {
    fun goToAuthScreen(activity: Activity) {
        val intent = Intent(activity, AuthActivity::class.java)
        activity.startActivity(intent)
    }

    fun goToMainScreen(activity: Activity) {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }
}