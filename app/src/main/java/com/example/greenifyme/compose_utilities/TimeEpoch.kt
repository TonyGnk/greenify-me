package com.example.greenifyme.compose_utilities

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun findEpochTimeFrom(givenDate: String): Long {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(givenDate)
    if (date != null) {
        return date.time
    }
    return 0
}


@SuppressLint("SimpleDateFormat")
fun getFullTimeFromEpoch(date: Long): String {
    return SimpleDateFormat("HH:mm dd/MM/yy").format(date)
}

//Get only the time
@SuppressLint("SimpleDateFormat")
fun getTimeFromEpoch(date: Long): String {
    return SimpleDateFormat("HH:mm").format(date)
}