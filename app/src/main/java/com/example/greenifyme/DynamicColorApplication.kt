package com.example.greenifyme

import android.app.Application
import com.google.android.material.color.DynamicColors

class DynamicColorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}