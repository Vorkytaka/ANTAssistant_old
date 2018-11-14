package com.assistant.ant.solidlsnake.antassistant.presentation.model

import com.assistant.ant.solidlsnake.antassistant.domain.entity.UserData

class UserDataUI(
        userData: UserData
) {
    val data: List<Pair<String, String>> = listOf(
            "Учетная запись" to userData.accountName,
            "Код плательщика" to userData.userId,
            "Состояние" to if (userData.state.status) "Активна" else "Неактивна"
    )
}