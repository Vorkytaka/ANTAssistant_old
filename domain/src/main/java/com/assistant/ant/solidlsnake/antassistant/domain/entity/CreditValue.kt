package com.assistant.ant.solidlsnake.antassistant.domain.entity

/**
 * Класс описывающий все возможные кейсы
 * для значения кредита доверия
 */
enum class CreditValue {
    V_0,
    V_100,
    V_200,
    V_300,

    /**
     * Значение определяющее кредит доверия
     * для дополнительных двух дней
     */
    V_BONUS
}