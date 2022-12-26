package com.sidukov.descriptionbin.descriptionbin.domain

import java.io.Serializable

data class Number(
    val length: Int,
    val luhn: Boolean
) : Serializable