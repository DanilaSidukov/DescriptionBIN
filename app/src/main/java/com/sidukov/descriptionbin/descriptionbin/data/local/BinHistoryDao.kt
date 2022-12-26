package com.sidukov.descriptionbin.descriptionbin.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BinHistoryDao {

    @Query("SELECT * FROM entityhistorybin")
    suspend fun getAll(): List<EntityHistoryBin>

    @Insert
    suspend fun insertData(historyData: EntityHistoryBin)

    @Delete
    suspend fun deleteData(historyData: List<EntityHistoryBin>)

}
