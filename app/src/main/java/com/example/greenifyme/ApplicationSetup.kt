package com.example.greenifyme

import android.app.Application
import com.example.greenifyme.data.account.AccountDao
import com.example.greenifyme.data.account.AccountDatabase
import com.google.android.material.color.DynamicColors

class ApplicationSetup : Application() {
    lateinit var accountRepository: AccountDao
    override fun onCreate() {
        super.onCreate()
        // Apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)

        // Load the account repository
        accountRepository = AccountDatabase.getDatabase(this).accountDao()
        //Add more repositories here
    }
}