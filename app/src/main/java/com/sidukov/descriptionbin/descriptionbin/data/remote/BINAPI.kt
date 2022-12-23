package com.sidukov.descriptionbin.descriptionbin.data.remote

import com.sidukov.descriptionbin.descriptionbin.domain.DataBIN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path

interface BINAPI {

    @GET("/{bin}")
    suspend fun getDataFromBin(
        @Path("bin") bin: String
    ): DataBIN


//            DataBIN


}

interface ApiCallback<T> {
    fun onException(error: Throwable)

    fun onError(error: String)

    fun onSuccess(t: T)
}