package com.sidukov.descriptionbin.descriptionbin.data.remote

import com.sidukov.descriptionbin.descriptionbin.domain.DataBin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {

    @GET("/{bin}")
    suspend fun getDataFromBin(
        @Path("bin") bin: String
    ): Response<DataBin>

}