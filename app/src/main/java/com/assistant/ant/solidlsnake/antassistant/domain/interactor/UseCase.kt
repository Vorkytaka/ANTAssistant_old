package com.assistant.ant.solidlsnake.antassistant.domain.interactor

interface UseCase<P, R> {
    suspend fun execute(params: P): R
}