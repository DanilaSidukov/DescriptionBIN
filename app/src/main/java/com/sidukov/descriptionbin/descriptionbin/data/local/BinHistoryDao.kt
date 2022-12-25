package com.sidukov.descriptionbin.descriptionbin.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface BinHistoryDao {
    @Query("SELECT * FROM entityhistorybin")
    suspend fun getAll(): List<EntityHistoryBIN>

    @Insert
    suspend fun insertData(historyData: EntityHistoryBIN)

    @Delete
    suspend fun deleteData (historyData: List<EntityHistoryBIN>)
}
