package com.sidukov.descriptionbin.descriptionbin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database (entities = [EntityHistoryBIN::class], version = 1)
abstract class DatabaseHistoryBIN : RoomDatabase() {

    abstract fun daoHistoryBIN(): BinHistoryDao

}