package com.assistant.ant.solidlsnake.antassistant.domain.repository

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue
import com.assistant.ant.solidlsnake.antassistant.domain.state.*
import kotlinx.coroutines.channels.ReceiveChannel

interface IRepository {
    /**
     * Проверка залогинен ли пользователь
     */
    suspend fun isLogged(): ReceiveChannel<IsLoggedState>

    /**
     * Авторизация пользователя
     */
    suspend fun login(credentials: Credentials): ReceiveChannel<AuthState>

    /**
     * Выход из приложения
     */
    suspend fun logout(): ReceiveChannel<Unit>

    /**
     * Получение информации о пользователе
     */
    suspend fun getUserData(): ReceiveChannel<GetUserDataState>

    /**
     * Определение, можем ли мы выставить кредит доверия
     */
    suspend fun canSetCredit(): ReceiveChannel<CanSetCreditState>

    /**
     * Получение максимально возможного значения кредита доверия
     */
    suspend fun maxAvailableCredit(): ReceiveChannel<MaxAvailableCreditState>

    /**
     * Установление кредита доверия
     */
    suspend fun setCredit(creditValue: CreditValue): ReceiveChannel<Nothing>
}