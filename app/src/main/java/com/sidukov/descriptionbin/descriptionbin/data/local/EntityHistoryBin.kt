package com.sidukov.descriptionbin.descriptionbin.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityHistoryBin(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "bin") val bin: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "bank") val bank: String,
    @ColumnInfo(name = "time") val time: String
)
