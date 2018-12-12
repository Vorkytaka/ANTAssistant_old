package com.assistant.ant.solidlsnake.antassistant.domain.repository

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import kotlinx.coroutines.channels.ReceiveChannel

interface IRepository {
    /**
     * Проверка залогинен ли пользователь в приложение.
     *
     * @return  true если залогинен
     *          false если нет
     */
    suspend fun isLogged(): ReceiveChannel<Boolean>

    /**
     * Авторизация пользователя.
     *
     * @return  true если вход успешен
     *          false иначе
     */
    suspend fun auth(credentials: Credentials): ReceiveChannel<Boolean>

    /**
     * Получение информации о пользователе
     */
    suspend fun getUserData(): ReceiveChannel<UserData?>

    /**
     * Определение, можем ли мы выставить кредит доверия
     */
    suspend fun canSetCredit(): ReceiveChannel<Boolean>

    /**
     * Получение максимально возможного значения кредита доверия
     */
    suspend fun maxAvailableCredit(): ReceiveChannel<CreditValue>

    suspend fun setCredit(creditValue: CreditValue): ReceiveChannel<Boolean>
}