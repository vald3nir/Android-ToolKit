package com.vald3nir.core_ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class CoreFragment : Fragment(), ViewModelController {

    private var coreActivity: CoreActivity? = null
    val onError: (e: Exception?) -> Unit = { showMessage(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel()
        if (activity is CoreActivity) {
            coreActivity = activity as CoreActivity
        }
    }

    override fun requireActivityContext(): Activity? {
        return activity
    }

    override fun onBackPressed() {
        coreActivity?.onBackPressed()
    }

    override fun showMessage(e: Exception?) {
        e?.printStackTrace()
        coreActivity?.showMessage(e?.message)
    }

    override fun showMessage(message: String?) {
        coreActivity?.showMessage(message)
    }

    override fun showMessage(message: Int) {
        coreActivity?.showMessage(message)
    }
}