package com.example.gensounosakura.report_app_free.Model

import java.text.SimpleDateFormat
import java.util.*

class Timer {

    fun getUnixTime(): Long {
        return System.currentTimeMillis() / 1000
    }

    fun getToday(): String {
        val date = Date()
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        return format.format(date)
    }
}
