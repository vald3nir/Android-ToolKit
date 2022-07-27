package com.vald3nir.core_ui

interface ViewModelController {
    fun registerViewModel()
    fun requireActivityContext(): CoreActivity?
    fun getString(id: Int): String
    fun onBackPressed()
    fun showMessage(e: Exception?)
    fun showMessage(message: String?)
    fun showMessage(message: Int)
    fun finishWithResult()
}