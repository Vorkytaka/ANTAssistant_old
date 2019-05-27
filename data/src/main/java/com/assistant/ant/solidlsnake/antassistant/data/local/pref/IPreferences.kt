package com.assistant.ant.solidlsnake.antassistant.data.local.pref

interface IPreferences {
    fun saveInt(key: String, value: Int)
    fun getInt(key: String, default: Int): Int

    fun saveBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, default: Boolean): Boolean

    fun saveFloat(key: String, value: Float)
    fun getFloat(key: String, default: Float): Float

    fun saveLong(key: String, value: Long)
    fun getLong(key: String, default: Long): Long

    fun saveString(key: String, value: String?)
    fun getString(key: String, default: String?): String?
}