package com.assistant.ant.solidlsnake.antassistant.domain.state

sealed class IsLoggedState {
    object AuthError : IsLoggedState()
    object Success : IsLoggedState()
    object Error : IsLoggedState()
}