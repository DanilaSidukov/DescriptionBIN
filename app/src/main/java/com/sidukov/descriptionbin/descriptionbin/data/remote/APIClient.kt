package com.sidukov.descriptionbin.descriptionbin.data.remote

import com.google.gson.GsonBuilder
import com.sidukov.descriptionbin.descriptionbin.domain.Bank
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object APIClient {

    private const val BASE_URL = "https://lookup.binlist.net"
    private var retrofitBIN: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        ))
        .build()

    var binApiClient: BINAPI = retrofitBIN.create(BINAPI::class.java)

    fun<T> buildService(service: Class<T>): T{
        return retrofitBIN.create(service)
    }
}