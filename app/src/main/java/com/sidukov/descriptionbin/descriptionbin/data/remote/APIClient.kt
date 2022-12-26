package com.sidukov.descriptionbin.descriptionbin.data.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private const val BASE_URL = "https://lookup.binlist.net"
    private var retrofitBin: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build()

    var binApiClient: BinApi = retrofitBin.create(BinApi::class.java)

}