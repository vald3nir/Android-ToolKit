package com.vald3nir.toolkit.core

import android.app.Activity
import androidx.lifecycle.ViewModel

open class CoreViewModel : ViewModel() {

    protected var controller: ViewModelController? = null

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

    fun finish() {
        requireActivityContext()?.apply {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}