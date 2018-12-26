package com.assistant.ant.solidlsnake.antassistant.domain.entity

/**
 * Информация о тарифе
 */
data class Tariff(
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