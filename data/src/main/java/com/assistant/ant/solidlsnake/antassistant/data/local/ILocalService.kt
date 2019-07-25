package com.assistant.ant.solidlsnake.antassistant.data.local

import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

interface ILocalService {
    /**
     * Получение закэшированных данных пользователя
     */
    suspend fun getUserData(): UserDataModel?

    /**
     * Сохранение данных пользователя в кэш
     */
    suspend fun saveUserData(data: UserData)

    /**
     * Получение авторизационных данных пользователя
     */
    suspend fun getCredentials(): Credentials?

    /**
     * Сохранение логина и пароля пользователя
     */
    suspend fun setCredentials(credentials: Credentials)

    /**
     * Отчистка всех локальных данных,
     * включая данные авторизации
     */
    suspend fun clear()
}