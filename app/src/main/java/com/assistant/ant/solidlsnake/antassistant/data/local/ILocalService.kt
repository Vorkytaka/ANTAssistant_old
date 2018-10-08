package com.assistant.ant.solidlsnake.antassistant.data.local

import com.assistant.ant.solidlsnake.antassistant.data.local.model.UserDataModel

interface ILocalService {
    suspend fun getUserData(): UserDataModel
}