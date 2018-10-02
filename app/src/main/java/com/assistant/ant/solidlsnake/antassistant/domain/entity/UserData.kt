package com.assistant.ant.solidlsnake.antassistant.domain.entity

/**
 * Информация о пользователе
 */
data class UserData(
        /**
         * Учетная запись
         */
        val accountName: String,

        /**
         * Уникальный код плательщика
         */
        val userId: String,

        /**
         * Информация о тарифе
         */
        val tariff: Tafiff,

        /**
         * Текущее состояние учетной записи
         */
        val state: State
)

/**
 * Текущее состояние учетной записи
 */
data class State(
        /**
         * Состояние счета, в рублях
         */
        val balance: Double,

        /**
         * Скачано за текущий месяц (Мб)
         */
        val downloaded: Int,

        /**
         * Статус учетной записи
         */
        val status: Boolean,

        /**
         * Кредит доверия, в рублях
         */
        val credit: Int
)

/**
 * Информация о тарифе
 */
data class Tafiff(
        /**
         * Название тарифа
         */
        val name: String,

        /**
         * Максимальная скорость загрузки (Мб)
         */
        val downloadSpeed: Int,

        /**
         * Максимальная скорость отдачи (Мб)
         */
        val uploadSpeed: Int,

        /**
         * Цена за месяц, в рублях
         */
        val price: Double
)