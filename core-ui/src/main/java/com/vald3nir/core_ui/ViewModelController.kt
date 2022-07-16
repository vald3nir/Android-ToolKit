package com.vald3nir.core_ui

import android.app.Activity

interface ViewModelController {
    fun registerViewModel()
    fun requireActivityContext(): Activity?
    fun getString(id: Int): String
    fun onBackPressed()
    fun showMessage(e: Exception?)
    fun showMessage(message: String?)
    fun showMessage(message: Int)
}