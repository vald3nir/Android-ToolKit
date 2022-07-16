package com.vald3nir.core_ui

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vald3nir.core_ui.extensions.hideKeyboard

abstract class CoreActivity : AppCompatActivity(), ViewModelController {

    private var toast: Toast? = null

    val onError: (e: Exception?) -> Unit = {
        it?.printStackTrace()
        showMessage(it?.message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel()
    }

    override fun onDestroy() {
        hideKeyboard()
        super.onDestroy()
    }

    override fun requireActivityContext(): Activity? {
        return this
    }

    override fun showMessage(e: Exception?) {
        showMessage(e?.message)
    }

    override fun showMessage(message: Int) {
        showMessage(getString(message))
    }

    override fun showMessage(message: String?) {
        toast?.cancel()
        if (message != null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
            toast?.show()
        }
    }
}