package com.assistant.ant.solidlsnake.antassistant.domain.entity

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