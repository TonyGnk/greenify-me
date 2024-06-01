package com.example.greenifyme.data.account

import android.os.Bundle
import com.example.greenifyme.data.Account

fun Account.toBundle(): Bundle {
    return Bundle().apply {
        putInt("accountId", accountId)
        putString("name", name)
        putString("email", email)
        putString("password", password)
        putBoolean("isAdmin", isAdmin)
        putInt("points", points)
        putLong("createdAt", createdAt)
    }
}

fun Bundle.toAccount(): Account {
    return Account(
        accountId = getInt("accountId"),
        name = getString("name", ""),
        email = getString("email", ""),
        password = getString("password", ""),
        isAdmin = getBoolean("isAdmin"),
        points = getInt("points"),
        createdAt = getLong("createdAt")
    )
}