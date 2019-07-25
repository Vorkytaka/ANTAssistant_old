package com.assistant.ant.solidlsnake.antassistant.domain.repository

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue
import com.assistant.ant.solidlsnake.antassistant.domain.state.*

interface IRepository {
    /**
     * Проверка залогинен ли пользователь
     */
    suspend fun isLogged(): IsLoggedState

    /**
     * Авторизация пользователя
     */
    suspend fun login(credentials: Credentials): AuthState

    /**
     * Выход из приложения
     */
    suspend fun logout()

    /**
     * Получение информации о пользователе
     */
    suspend fun getUserData(): GetUserDataState

    /**
     * Определение, можем ли мы выставить кредит доверия
     */
    suspend fun canSetCredit(): CanSetCreditState

    /**
     * Получение максимально возможного значения кредита доверия
     */
    suspend fun maxAvailableCredit(): MaxAvailableCreditState

    /**
     * Установление кредита доверия
     */
    suspend fun setCredit(creditValue: CreditValue)
}