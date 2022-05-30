package com.vald3nir.diskwater.presentation.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vald3nir.diskwater.common.BaseFragment
import com.vald3nir.toolkit.core.CoreFragment
import com.vald3nir.diskwater.databinding.FragmentPaymentMethodsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentMethodsFragment : BaseFragment() {

    private val viewModel: OrderViewModel by viewModel()
    lateinit var binding: FragmentPaymentMethodsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentMethodsBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        viewModel.appView = appView
        binding.apply {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}