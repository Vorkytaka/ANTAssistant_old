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
         * Ваш DynDNS
         */
        val dynDns: String,

        /**
         * Информация о тарифе
         */
        val tariff: Tariff,

        /**
         * Текущее состояние учетной записи
         */
        val state: State
) {
    val pricePerDay: Double = this.tariff.price / 30

    val daysLeft: Int = ((this.state.balance + this.state.credit) / pricePerDay).toInt()
}