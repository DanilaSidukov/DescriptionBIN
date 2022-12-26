package com.sidukov.descriptionbin.descriptionbin.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Bank(
    @SerializedName("city")
    val city: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("url")
    val url: String
) : Serializable