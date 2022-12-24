package com.sidukov.descriptionbin.descriptionbin.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

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
    val prepaid: Boolean,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String,
) : Serializable