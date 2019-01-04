package com.assistant.ant.solidlsnake.antassistant.domain

/**
 * Интерфейс маппинга данных
 *
 * @param X тип из которого преобразуем
 * @param Y тип в который преобразуем
 */
typealias Mapper<X, Y> = (X) -> Y