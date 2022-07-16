package com.vald3nir.utils

fun createNumbersArray(start: Int, end: Int): ArrayList<String> {
    val numbers = arrayListOf<String>()
    for (i in start..end) numbers.add(i.toString())
    return numbers
}