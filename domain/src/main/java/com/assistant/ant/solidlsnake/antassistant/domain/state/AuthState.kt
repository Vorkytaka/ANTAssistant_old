package com.assistant.ant.solidlsnake.antassistant.domain.state

sealed class AuthState {
    object Success : AuthState()
    object Error : AuthState()
}