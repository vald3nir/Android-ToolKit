package com.vald3nir.toolkit.data.repository.local

import android.content.Context
import android.content.SharedPreferences

private fun Context.getLocalPreferences(): SharedPreferences {
    return this.getSharedPreferences("Local-Preferences", Context.MODE_PRIVATE)
}

private fun Context.getLocalPreferencesEditor(): SharedPreferences.Editor {
    return this.getLocalPreferences().edit()
}

fun Context.saveDataJson(key: String, data: String?) {
    val sharedPref = getLocalPreferencesEditor()
    sharedPref.putString(key, data)
    sharedPref.apply()
}

fun Context.loadDataJson(key: String): String? {
    val sharedPref = getLocalPreferences()
    return sharedPref.getString(key, null)
}