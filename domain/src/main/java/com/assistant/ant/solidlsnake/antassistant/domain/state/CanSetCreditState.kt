package com.assistant.ant.solidlsnake.antassistant.domain.state

sealed class CanSetCreditState {
    object Can : CanSetCreditState()
    object Cannot : CanSetCreditState()
}