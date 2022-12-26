package com.sidukov.descriptionbin.descriptionbin.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.sidukov.descriptionbin.descriptionbin.data.local.BinHistoryDao
import com.sidukov.descriptionbin.descriptionbin.data.local.EntityHistoryBin
import com.sidukov.descriptionbin.descriptionbin.data.remote.BinApi
import com.sidukov.descriptionbin.descriptionbin.domain.DataBin
import com.sidukov.descriptionbin.descriptionbin.getSplitStringBIN
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CardBinRepository(
    private val binApi: BinApi,
    private val historyDao: BinHistoryDao
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getData(argumentBin: String): ApiResponse<DataBin> {

        val response = binApi.getDataFromBin(
            bin = argumentBin
        )

        var jsonObject = " "

        if (response.code() !in 200..299) {
            jsonObject = response.errorBody()!!.charStream().toString()
        }

        if (response.isSuccessful) {
            historyDao.insertData(
                EntityHistoryBin(
                    bin = getSplitStringBIN(argumentBin),
                    country = response.body()?.country?.name ?: "No Data",
                    bank = response.body()?.bank?.name ?: "No Data",
                    time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM HH:mm"))
                )
            )
        }

        return ApiResponse(
            data = if (response.isSuccessful) response.body()!! else null,
            error = if (response.isSuccessful) null else jsonObject
        )
    }

    suspend fun getBinHistory() = historyDao.getAll()

    suspend fun deleteBinHistory() = historyDao.deleteData(getBinHistory())

}

class ApiResponse<T>(val data: T?, val error: String?)