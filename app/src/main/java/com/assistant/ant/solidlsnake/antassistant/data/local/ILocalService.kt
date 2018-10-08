package com.assistant.ant.solidlsnake.antassistant.data.local

import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel
import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

interface ILocalService {
    suspend fun getUserData(): UserDataModel
    suspend fun saveUserData(data: UserData)
}