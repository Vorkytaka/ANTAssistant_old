package com.assistant.ant.solidlsnake.antassistant.data.local.pref

import android.content.Context
import android.preference.PreferenceManager

class SharedPreferences(
        context: Context,
        private val preferencesName: String? = null
) : IPreferences {

    private val sharedPreferences by lazy {
        if (preferencesName == null) {
            PreferenceManager.getDefaultSharedPreferences(context)
        } else {
            context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        }
    }

    override fun saveInt(key: String, value: Int) {
        sharedPreferences.edit()
                .putInt(key, value)
                .apply()
    }

    override fun getInt(key: String, default: Int): Int {
        return sharedPreferences.getInt(key, default)
    }

    override fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit()
                .putBoolean(key, value)
                .apply()
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    override fun saveFloat(key: String, value: Float) {
        sharedPreferences.edit()
                .putFloat(key, value)
                .apply()
    }

    override fun getFloat(key: String, default: Float): Float {
        return sharedPreferences.getFloat(key, default)
    }

    override fun saveLong(key: String, value: Long) {
        sharedPreferences.edit()
                .putLong(key, value)
                .apply()
    }

    override fun getLong(key: String, default: Long): Long {
        return sharedPreferences.getLong(key, default)
    }

    override fun saveString(key: String, value: String?) {
        sharedPreferences.edit()
                .putString(key, value)
                .apply()
    }

    override fun getString(key: String, default: String?): String? {
        return sharedPreferences.getString(key, default)
    }
}