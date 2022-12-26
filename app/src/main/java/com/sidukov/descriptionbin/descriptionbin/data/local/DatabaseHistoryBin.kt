package com.sidukov.descriptionbin.descriptionbin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EntityHistoryBin::class], version = 1)
abstract class DatabaseHistoryBin : RoomDatabase() {

    abstract fun daoHistoryBin(): BinHistoryDao

}