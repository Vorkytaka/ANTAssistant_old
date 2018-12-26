package com.assistant.ant.solidlsnake.antassistant.domain

/**
 * Интерфейс маппинга данных
 */
interface Mapper<X, Y> {
    fun map(from: X): Y
    fun map(from: Collection<X>): Collection<Y> {
        return from.map { map(it) }
    }
}