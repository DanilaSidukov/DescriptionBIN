package com.sidukov.descriptionbin.descriptionbin.data

import androidx.core.text.htmlEncode
import com.sidukov.descriptionbin.descriptionbin.data.remote.APIClient
import com.sidukov.descriptionbin.descriptionbin.data.remote.BINAPI
import com.sidukov.descriptionbin.descriptionbin.domain.DataBIN
import com.sidukov.descriptionbin.descriptionbin.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardBINRepository(
    private val binAPI: BINAPI,
    private val bin: String
) {

    suspend fun getData(): List<DataBIN> {

        println("BIN = $bin")

        val binAPI = binAPI.getDataFromBin(
            bin = bin
        )

        println("API = $binAPI")

        return listOf(
            DataBIN(
                bank = binAPI.bank,
                brand = binAPI.brand,
                country = binAPI.country,
                number = binAPI.number,
                prepaid = getBooleanString(binAPI.prepaid),
                scheme = binAPI.scheme,
                type = binAPI.type
            )
        )
    }
}

fun getBooleanString(boolean: String): String {
    return if (boolean == "true") "Yes"
    else "No"
}