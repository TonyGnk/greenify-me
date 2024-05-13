package com.example.greenifyme

import android.app.Application
import com.example.greenifyme.data.account.AccountDao
import com.example.greenifyme.data.account.AccountDatabase
import com.example.greenifyme.data.account.AccountRepository
import com.example.greenifyme.data.material.MaterialDao
import com.example.greenifyme.data.material.MaterialDatabase
import com.example.greenifyme.data.record.RecordDao
import com.example.greenifyme.data.record.RecordDatabase
import com.example.greenifyme.data.record.RecordRepository
import com.example.greenifyme.data.tracked_material.TrackedMaterialDao
import com.example.greenifyme.data.tracked_material.TrackedMaterialDatabase
import com.google.android.material.color.DynamicColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ApplicationSetup : Application() {
    lateinit var accountRepository: AccountRepository
    lateinit var recordRepository: RecordRepository
    lateinit var trackedMaterialRepository: TrackedMaterialDao
    lateinit var materialRepository: MaterialDao

    override fun onCreate() {
        super.onCreate()
        // Apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)

        // Load the account repository
        accountRepository = AccountRepository(AccountDatabase.getDatabase(this).accountDao())
        recordRepository = RecordRepository(RecordDatabase.getDatabase(this).recordDao())
        trackedMaterialRepository = TrackedMaterialDatabase.getDatabase(this).trackedMaterialDao()
        materialRepository = MaterialDatabase.getDatabase(this).materialDao()
        //Add more repositories here
    }
}

fun getScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)