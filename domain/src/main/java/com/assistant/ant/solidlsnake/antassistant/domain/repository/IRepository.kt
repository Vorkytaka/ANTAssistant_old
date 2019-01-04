package com.assistant.ant.solidlsnake.antassistant.domain.repository

import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData
import kotlinx.coroutines.channels.ReceiveChannel

interface IRepository {
    /**
     * Получение авторизационных данных пользователя
     */
    suspend fun getCredentials(): ReceiveChannel<Credentials?>

    /**
     * Сохранение авторизационных данных пользователя
     */
    suspend fun setCredentials(credentials: Credentials)

    /**
     * Проверка корректности данных для входа
     *
     * @param credentials данные которые необходимо проверить
     * @return true если данные для входа корректны, иначе false
     */
    suspend fun auth(credentials: Credentials): ReceiveChannel<Boolean>

    /**
     * Получение информации о пользователе
     */
    suspend fun getUserData(): ReceiveChannel<UserData>

    /**
     * Определение, можем ли мы выставить кредит доверия
     *
     * @return  true - если можем выставить кредит доверия
     *          false - иначе
     */
    suspend fun canSetCredit(): ReceiveChannel<Boolean>

    /**
     * Получение максимально возможного значения кредита доверия
     *
     * @return максимально возможный на данный момент кредит значения
     */
    suspend fun maxAvailableCredit(): ReceiveChannel<CreditValue>

    /**
     * Установление кредита доверия
     *
     * @return  true - если кредит доверия установлен успешно
     *          false - в противном случае
     */
    suspend fun setCredit(creditValue: CreditValue): ReceiveChannel<Boolean>
}