package com.assistant.ant.solidlsnake.antassistant.data.local.account

import com.assistant.ant.solidlsnake.antassistant.domain.entity.AuthData

interface IAccountHolder {
    /**
     * Проверка, есть ли у пользователя аккаунт на телефоне
     */
    fun hasAccount(): Boolean

    /**
     * Сохранение данных аккаунта пользователя
     *
     * @param data данные пользователя
     */
    fun saveAccount(data: AuthData)

    /**
     * Получение данных аккаунта пользователя
     *
     * @return данные пользователя
     */
    fun getAccount(): AuthData

    /**
     * Удаление всех аккаунтов пользователя
     */
    fun removeAccount()
}