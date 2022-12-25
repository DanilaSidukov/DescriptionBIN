package com.sidukov.descriptionbin.descriptionbin.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class EntityHistoryBIN(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "BIN") val bin: String,
    @ColumnInfo(name = "Country") val country: String,
    @ColumnInfo(name = "Bank") val bank: String,
    @ColumnInfo(name = "Time") val time: String
)
