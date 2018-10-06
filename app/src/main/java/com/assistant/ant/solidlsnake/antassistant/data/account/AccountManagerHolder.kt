package com.assistant.ant.solidlsnake.antassistant.data.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import com.assistant.ant.solidlsnake.antassistant.BuildConfig

object AccountManagerHolder {
    private var manager: AccountManager? = null
    private var accountType: String = ""

    fun init(context: Context) {
        manager = AccountManager.get(context)
        accountType = BuildConfig.ACCOUNT_TYPE
    }

    fun setAccount(login: String, password: String) {
        checkManager()

        val account = Account(login, accountType)
        manager!!.addAccountExplicitly(account, password, null)
    }

    fun removeAccount() {
        checkManager()

        val accounts = manager!!.getAccountsByType(accountType)
        for (account in accounts)
            manager!!.removeAccount(account, null, null)
    }

    fun hasAccount(): Boolean {
        checkManager()

        val accounts = manager!!.getAccountsByType(accountType)
        return accounts != null && accounts.isNotEmpty()
    }

    fun getAccount(): Account {
        checkManager()

        return manager!!.getAccountsByType(accountType).first()
    }

    fun getPassword(): String {
        checkManager()

        val account = getAccount()
        return manager!!.getPassword(account)
    }

    private fun checkManager() {
        if (manager == null) throw IllegalStateException("AccountManagerHolder must be initial")
    }
}