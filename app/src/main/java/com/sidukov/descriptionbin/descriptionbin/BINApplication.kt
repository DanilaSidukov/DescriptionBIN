package com.sidukov.descriptionbin.descriptionbin

import android.app.Application
import androidx.room.Room
import com.sidukov.descriptionbin.descriptionbin.data.local.DatabaseHistoryBIN

class BINApplication : Application() {

    companion object {
        lateinit var database: DatabaseHistoryBIN
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            DatabaseHistoryBIN::class.java,
            "history-list"
        ).build()
    }



}