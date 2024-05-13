package com.example.greenifyme

import android.app.Application
import com.example.greenifyme.data.GreenDatabase
import com.example.greenifyme.data.GreenRepository
import com.example.greenifyme.data.account.AccountDao
import com.example.greenifyme.data.account.AccountDatabase
import com.example.greenifyme.data.account.AccountRepository
import com.example.greenifyme.data.material.MaterialDatabase
import com.example.greenifyme.data.material.MaterialRepository
import com.example.greenifyme.data.record.RecordDao
import com.example.greenifyme.data.record.RecordDatabase
import com.example.greenifyme.data.record.RecordRepository
import com.google.android.material.color.DynamicColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ApplicationSetup : Application() {
    lateinit var greenRepository: GreenRepository

    //Depreciated
    lateinit var accountRepository: AccountDao
    lateinit var recordRepository: RecordDao

    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
        super.onCreate()
        // Apply dynamic color

        // Load the account repository
        greenRepository = GreenRepository(GreenDatabase.getDatabase(this).dao())

        //Depreciated
        accountRepository = AccountDatabase.getDatabase(this).accountDao()
        recordRepository = RecordDatabase.getDatabase(this).recordDao()
    }
}

fun getScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)