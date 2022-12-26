package com.sidukov.descriptionbin.descriptionbin

fun getSplitStringBIN(stringBIN: String): String {
    var tempStringBIN = " "
    var count = 0
    val stringChar = stringBIN.toCharArray()
    for (char in stringChar) {
        if (count == 4) {
            tempStringBIN += " $char"
            count = 0
        } else {
            tempStringBIN += char
            count += 1
        }
    }
    return tempStringBIN
}

fun Boolean.toResponseString(): String = if (this) "Yes" else "No"