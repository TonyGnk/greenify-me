package com.example.greenifyme

import android.app.Application
import com.example.greenifyme.data.GreenDatabase
import com.example.greenifyme.data.GreenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ApplicationSetup : Application() {
    lateinit var greenRepository: GreenRepository

    override fun onCreate() {
        //DynamicColors.applyToActivitiesIfAvailable(this)
        super.onCreate()

        // Load the account repository
        greenRepository = GreenRepository(GreenDatabase.getDatabase(this).dao())
    }
}

fun getScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)