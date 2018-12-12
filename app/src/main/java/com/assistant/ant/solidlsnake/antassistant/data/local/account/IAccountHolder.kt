package com.assistant.ant.solidlsnake.antassistant.data.local.account

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials

interface IAccountHolder {
    /**
     * Проверка, есть ли у пользователя аккаунт на телефоне
     */
    fun hasAccount(): Boolean

    /**
     * Сохранение данных аккаунта пользователя
     *
     * @param credentials данные пользователя
     */
    fun saveAccount(credentials: Credentials)

    /**
     * Получение данных аккаунта пользователя
     *
     * @return данные пользователя
     */
    fun getAccount(): Credentials

    /**
     * Удаление всех аккаунтов пользователя
     */
    fun removeAccount()
}