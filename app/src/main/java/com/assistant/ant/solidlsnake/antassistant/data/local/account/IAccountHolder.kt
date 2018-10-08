package com.assistant.ant.solidlsnake.antassistant.data.local.account

import com.assistant.ant.solidlsnake.antassistant.data.local.model.AccountData

interface IAccountHolder {
    fun hasAccount(): Boolean
    fun saveAccount(data: AccountData)
    fun getAccount(): AccountData // todo: поменять тип
    fun removeAccount()
}