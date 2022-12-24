package com.sidukov.descriptionbin.descriptionbin.data

import androidx.core.text.htmlEncode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sidukov.descriptionbin.descriptionbin.data.remote.APIClient
import com.sidukov.descriptionbin.descriptionbin.data.remote.BINAPI
import com.sidukov.descriptionbin.descriptionbin.domain.DataBIN
import com.sidukov.descriptionbin.descriptionbin.ui.MainActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.typeOf

class CardBINRepository(
    private val binAPI: BINAPI
) {

    suspend fun getData(argumentBin: String): ApiResponse <DataBIN> {

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

        println("JSONOBJ = $jsonObject")

        return ApiResponse(
            data = if (binAPI.isSuccessful) binAPI.body()!! else null,
            error = if (binAPI.isSuccessful) null else jsonObject
        )
    }
}

class ApiResponse<T>(val data: T?, val error: String?)

fun getBooleanString(boolean: String): String {
    return if (boolean == "true") "Yes"
    else "No"
}

fun Boolean.toResponseString(): String = if (this) "Yes" else "No"