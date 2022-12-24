package com.sidukov.descriptionbin.descriptionbin.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class Number(
    val length: Int,
    val luhn: Boolean
) : Serializable