package com.assistant.ant.solidlsnake.antassistant.data.remote

import com.assistant.ant.solidlsnake.antassistant.data.remote.response.UserDataResponse

interface IRemoteService {
    /**
     * Авторизация пользователя в системе
     *
     * @param login имя пользователя
     * @param password пароль пользователя
     *
     * @return  true, если вход успешен
     *          false, если вход неуспешен
     */
    suspend fun auth(login: String, password: String): Boolean

    /**
     * Получение всей информации о пользователе,
     * которая отображается на главной странице
     *
     * @param login имя пользователя
     * @param password пароль пользователя
     *
     * @return  данные пользователя,
     *          null - если произошла ошибка
     */
    suspend fun getUserData(login: String, password: String): UserDataResponse?
}