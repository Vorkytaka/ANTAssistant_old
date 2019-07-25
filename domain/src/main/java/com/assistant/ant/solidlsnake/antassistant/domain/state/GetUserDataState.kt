package com.assistant.ant.solidlsnake.antassistant.domain.state

import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

sealed class GetUserDataState {
    data class Result(val data: UserData) : GetUserDataState()
    data class CachedResult(val data: UserData, val date: Long) : GetUserDataState()
    object NoUserData : GetUserDataState()
}