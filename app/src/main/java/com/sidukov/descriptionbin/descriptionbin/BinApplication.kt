package com.sidukov.descriptionbin.descriptionbin

import android.app.Application
import androidx.room.Room
import com.sidukov.descriptionbin.descriptionbin.data.local.DatabaseHistoryBin

class BinApplication : Application() {

    companion object {
        lateinit var database: DatabaseHistoryBin
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            DatabaseHistoryBin::class.java,
            "history-list"
        ).build()
    }

}