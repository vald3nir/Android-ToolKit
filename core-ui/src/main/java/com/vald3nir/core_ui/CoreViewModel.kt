package com.vald3nir.core_ui

import android.app.Activity
import androidx.lifecycle.ViewModel

open class CoreViewModel : ViewModel() {

    private var controller: ViewModelController? = null

    fun registerController(controller: ViewModelController) {
        this.controller = controller
    }

    fun requireActivityContext(): Activity? {
        return controller?.requireActivityContext()
    }

    fun getString(id: Int): String? {
        return controller?.getString(id)
    }

    fun showMessage(message: String?) {
        controller?.showMessage(message)
    }

    fun showError(it: Exception?) {
        controller?.showMessage(it?.message)
    }

    fun finishWithResult() {
        controller?.finishWithResult()
    }

    fun onBackPressed() {
        controller?.onBackPressed()
    }
}