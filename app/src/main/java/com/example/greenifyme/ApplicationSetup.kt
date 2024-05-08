package com.example.greenifyme

import android.app.Application
import com.example.greenifyme.data.account.AccountDao
import com.example.greenifyme.data.account.AccountDatabase
import com.example.greenifyme.data.record.RecordDao
import com.example.greenifyme.data.record.RecordDatabase
import com.google.android.material.color.DynamicColors

class ApplicationSetup : Application() {
    lateinit var accountRepository: AccountDao
    lateinit var recordRepository: RecordDao
    override fun onCreate() {
        super.onCreate()
        // Apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)

        // Load the account repository
        accountRepository = AccountDatabase.getDatabase(this).accountDao()
        recordRepository = RecordDatabase.getDatabase(this).recordDao()
        //Add more repositories here
    }
}