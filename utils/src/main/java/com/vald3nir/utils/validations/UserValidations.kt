package com.vald3nir.utils.validations

import android.util.Patterns

fun isNameValid(username: String): Boolean {
    return if (username.isNotBlank()) {
        username.trim().split("").size >= 2
    } else false
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordValid(password: String): Boolean {
    return password.length > 6
}