package com.assistant.ant.solidlsnake.antassistant.domain.state

import com.assistant.ant.solidlsnake.antassistant.domain.entity.CreditValue

sealed class MaxAvailableCreditState {
    data class Result(val creditValue: CreditValue) : MaxAvailableCreditState()
}