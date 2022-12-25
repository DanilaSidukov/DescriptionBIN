package com.sidukov.descriptionbin.descriptionbin.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.sidukov.descriptionbin.descriptionbin.data.local.BinHistoryDao
import com.sidukov.descriptionbin.descriptionbin.data.local.EntityHistoryBIN
import com.sidukov.descriptionbin.descriptionbin.data.remote.BINAPI
import com.sidukov.descriptionbin.descriptionbin.domain.DataBIN
import com.sidukov.descriptionbin.descriptionbin.ui.getSplitStringBIN
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CardBINRepository(
    private val binAPI: BINAPI,
    private val historyDao: BinHistoryDao
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getData(argumentBin: String): ApiResponse<DataBIN> {

        //"45132341"

        val binAPI = binAPI.getDataFromBin(
            bin = argumentBin
        )

        println(binAPI)

        println("Error body = ${binAPI.errorBody()}")
        var jsonObject = " "

        if (binAPI.code() !in 200..299){
            jsonObject = binAPI.errorBody()!!.charStream().toString()
        }

        if (binAPI.isSuccessful) historyDao.insertData(
            EntityHistoryBIN(
                bin = getSplitStringBIN(argumentBin),
                country = binAPI.body()?.country?.name.toString(),
                bank = binAPI.body()?.bank?.name.toString(),
                time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM HH:mm"))
            )
        )

        return ApiResponse(
            data = if (binAPI.isSuccessful) binAPI.body()!! else null,
            error = if (binAPI.isSuccessful) null else jsonObject
        )
    }

    suspend fun getBinHistory() = historyDao.getAll()

    suspend fun deleteBinHistory() = historyDao.deleteData(getBinHistory())

}

class ApiResponse<T>(val data: T?, val error: String?)

fun getBooleanString(boolean: String): String {
    return if (boolean == "true") "Yes"
    else "No"
}

fun Boolean.toResponseString(): String = if (this) "Yes" else "No"