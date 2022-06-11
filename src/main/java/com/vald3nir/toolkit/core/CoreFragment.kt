package com.vald3nir.toolkit.core

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vald3nir.toolkit.data.dto.BaseDTO

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

    fun putExtraDTO(baseDTO: BaseDTO?) {
        val bundle = Bundle()
        bundle.putSerializable("EXTRA", baseDTO)
        this.arguments = bundle
    }

    fun loadExtraDTO(): BaseDTO? {
        return arguments?.getSerializable("EXTRA") as BaseDTO?
    }
}