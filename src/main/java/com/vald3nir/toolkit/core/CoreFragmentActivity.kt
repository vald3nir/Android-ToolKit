package com.vald3nir.toolkit.core

import com.vald3nir.toolkit.utils.extensions.hideKeyboard
import org.koin.ext.getFullName

abstract class CoreFragmentActivity : CoreActivity() {

    override fun onBackPressed() {
        try {
            supportFragmentManager.popBackStack()
            val backStackCount = supportFragmentManager.backStackEntryCount
            if (backStackCount == 1) finish()
        } catch (e: Exception) {
            showMessage(e)
            finish()
        }
    }

    fun loadFragment(containerID: Int, fragment: CoreFragment) {
        hideKeyboard()

        val fragmentName = fragment.let { it::class.getFullName() }
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(containerID, fragment)
        fragmentTransaction.addToBackStack(fragmentName)
        fragmentTransaction.commitAllowingStateLoss()
    }

}