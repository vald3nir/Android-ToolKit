package com.vald3nir.toolkit.utils.extensions

import android.text.Editable
import com.google.gson.Gson
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

fun String.toFloatValue(): Float {
    this.trim().let {
        return if (it.isBlank()) 0.0f else it.toFloat()
    }
}

fun Float?.toMoney(): String {
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}

fun Float?.format(): String {
    val bd = BigDecimal(this?.toDouble() ?: 0.0f.toDouble())
    return bd.setScale(2, RoundingMode.HALF_UP).toString()
}

fun Editable?.format(): String {
    return this.toString().trim()
}

fun <T> String?.toDTO(classOfT: Class<T>): T {
    return Gson().fromJson(this, classOfT)
}