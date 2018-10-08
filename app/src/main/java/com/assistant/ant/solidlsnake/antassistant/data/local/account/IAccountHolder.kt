package com.assistant.ant.solidlsnake.antassistant.data.local.account

import com.assistant.ant.solidlsnake.antassistant.data.local.model.AccountData

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
    fun saveAccount(data: AccountData)

    /**
     * Получение данных аккаунта пользователя
     *
     * @return данные пользователя
     */
    fun getAccount(): AccountData

    /**
     * Удаление всех аккаунтов пользователя
     */
    fun removeAccount()
}