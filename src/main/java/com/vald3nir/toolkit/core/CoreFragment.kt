package com.vald3nir.toolkit.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vald3nir.toolkit.data.BaseDTO

open class CoreFragment : Fragment() {

    var coreActivity: CoreActivity? = null
    var appView: AppView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity is CoreActivity) {
            coreActivity = activity as CoreActivity
        }
    }

    fun showMessage(message: String?) {
        coreActivity?.showMessage(message)
    }

    fun showMessage(message: Int) {
        coreActivity?.showMessage(message)
    }

    fun showLoading(show: Boolean) {
        coreActivity?.showLoading(show)
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