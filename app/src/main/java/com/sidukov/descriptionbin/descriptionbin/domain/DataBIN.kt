package com.sidukov.descriptionbin.descriptionbin.domain

import com.google.gson.annotations.SerializedName

data class DataBIN(
    @SerializedName("bank")
    val bank: Bank,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("country")
    val country: Country,
    @SerializedName("number")
    val number: Number,
    @SerializedName("prepaid")
    val prepaid: String,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String
)