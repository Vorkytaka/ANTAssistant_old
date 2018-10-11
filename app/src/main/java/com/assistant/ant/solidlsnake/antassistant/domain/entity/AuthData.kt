package com.assistant.ant.solidlsnake.antassistant.domain.entity

/**
 * Данные для авторизации пользователя.
 *
 * @param login логин пользователя
 * @param password пароль пользователя
 */
data class AuthData(val login: String, val password: String)