package com.vald3nir.diskwater.common.core

import android.os.Bundle
import com.vald3nir.diskwater.R
import com.vald3nir.diskwater.common.extensions.hideKeyboard
import com.vald3nir.diskwater.databinding.ActivityEmptyBinding
import com.vald3nir.diskwater.domain.navigation.FragmentEnum
import com.vald3nir.diskwater.domain.navigation.ScreenNavigation
import org.koin.android.ext.android.inject
import org.koin.ext.getFullName

class ActivityEmpty : BaseActivity() {

    companion object {
        const val FRAGMENT_ENUM_PARAM = "FRAGMENT_ENUM_PARAM"
    }

    private val screenNavigation: ScreenNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEmptyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(intent.getSerializableExtra(FRAGMENT_ENUM_PARAM) as FragmentEnum)
    }

    fun loadFragment(fragmentEnum: FragmentEnum) {
        hideKeyboard()

        val fragment: BaseFragment = screenNavigation.createFragment(fragmentEnum)
        fragment.let { it.appView = this }

        val fragmentName = fragment.let { it::class.getFullName() }
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(fragmentName)
        fragmentTransaction.commitAllowingStateLoss()
    }
}