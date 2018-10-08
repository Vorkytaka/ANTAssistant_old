package com.assistant.ant.solidlsnake.antassistant.data.local

import com.assistant.ant.solidlsnake.antassistant.data.local.model.AccountData
import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

interface ILocalService {
    /**
     * Получение закэшированных данных пользователя
     */
    suspend fun getUserData(): UserDataModel

    /**
     * Сохранение данных пользователя в кэш
     */
    suspend fun saveUserData(data: UserData)

    /**
     * Проверка, есть ли у пользователя аккаунт
     */
    suspend fun hasAccount(): Boolean

    /**
     * Получение логина и пароля пользователя
     */
    suspend fun getAccountData(): AccountData

    /**
     * Сохранение логина и пароля пользователя
     */
    suspend fun setAccountData(data: AccountData)
}