package com.assistant.ant.solidlsnake.antassistant.data.local.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import com.assistant.ant.solidlsnake.antassistant.BuildConfig
import com.assistant.ant.solidlsnake.antassistant.domain.entity.AuthData

class AccountHolderImpl(context: Context) : IAccountHolder {
    override fun hasAccount(): Boolean {
        val accounts = manager.getAccountsByType(accountType)
        return accounts.isNotEmpty()
    }

    override fun saveAccount(data: AuthData) {
        val account = Account(data.login, accountType)
        manager.addAccountExplicitly(account, data.password, null)
    }

    override fun getAccount(): AuthData {
        val account = manager.getAccountsByType(accountType).first()
        val login = account.name
        val password = manager.getPassword(account)

        return AuthData(login, password)
    }

    override fun removeAccount() {
        val accounts = manager.getAccountsByType(accountType)
        for (account in accounts)
            manager.removeAccount(account, null, null)
    }

    private var manager: AccountManager = AccountManager.get(context)
    private var accountType: String = BuildConfig.ACCOUNT_TYPE
}