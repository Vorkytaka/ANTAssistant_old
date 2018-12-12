package com.assistant.ant.solidlsnake.antassistant.data.remote

import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse
import com.assistant.ant.solidlsnake.antassistant.domain.entity.Credentials
import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue

interface IRemoteService {
    /**
     * Авторизация пользователя в системе
     *
     * @return  true, если вход успешен
     *          false, если вход неуспешен
     */
    suspend fun auth(credentials: Credentials): Boolean

    /**
     * Получение всей информации о пользователе,
     * которая отображается на главной странице
     *
     * @return  данные пользователя,
     *          null - если произошла ошибка
     */
    suspend fun getUserData(credentials: Credentials): UserDataResponse?

    /**
     * Выставление кредита доверия
     */
    suspend fun setCredit(credentials: Credentials, creditValue: CreditValue): Boolean
}