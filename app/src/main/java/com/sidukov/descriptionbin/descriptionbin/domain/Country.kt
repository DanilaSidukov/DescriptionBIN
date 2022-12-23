package com.sidukov.descriptionbin.descriptionbin.domain

import com.google.gson.annotations.SerializedName

data class Country(
    val alpha2: String,
    val currency: String,
    @SerializedName("emoji")
    val emoji: String,
    @SerializedName("latitude")
    val latitude: Int,
    @SerializedName("longitude")
    val longitude: Int,
    @SerializedName("name")
    val name: String,
    val numeric: String
)