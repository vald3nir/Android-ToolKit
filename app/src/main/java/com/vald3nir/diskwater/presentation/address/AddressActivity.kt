package com.vald3nir.diskwater.presentation.address

import android.os.Bundle
import androidx.lifecycle.Observer
import com.vald3nir.diskwater.R
import com.vald3nir.diskwater.common.core.BaseActivity
import com.vald3nir.diskwater.common.extensions.afterTextChanged
import com.vald3nir.diskwater.common.extensions.hideKeyboard
import com.vald3nir.diskwater.data.dto.AddressDTO
import com.vald3nir.diskwater.data.form.AddressInputForm
import com.vald3nir.diskwater.databinding.ActivityAddressBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressActivity : BaseActivity() {

    private val viewModel: AddressViewModel by viewModel()
    private lateinit var binding: ActivityAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setupObservers()
        viewModel.loadAddress()
    }

    private fun initViews() {
        viewModel.appView = this
        binding.apply {
            toolbar.setupToolbar(title = getString(R.string.order_address))
        }
    }

    private fun setupObservers() {
        viewModel.addressFields.observe(this@AddressActivity, Observer {
            val addressFields = it ?: return@Observer
            fillAddress(addressFields)
        })
        viewModel.addressInputForm.observe(this@AddressActivity, Observer {
            val addressState = it ?: return@Observer
            fillErrorLayout(addressState)
        })
        binding.apply {
            edtCep.afterTextChanged(afterTextChanged = { viewModel.searchByCep(cep = it) })
            btnSave.setOnClickListener { saveAddress() }
        }
    }

    private fun fillAddress(addressFields: AddressDTO) {
        binding.apply {
            edtCep.setText(addressFields.cep)
            edtStreet.setText(addressFields.street)
            edtNumber.setText(addressFields.number)
            edtComplement.setText(addressFields.complement)
            edtDistrict.setText(addressFields.district)
        }
    }

    private fun fillErrorLayout(addressState: AddressInputForm) {
        binding.apply {
            edtCepLayout.error = addressState.cepError
            edtStreetLayout.error = addressState.streetError
            edtDistrictLayout.error = addressState.districtError
        }
    }

    private fun saveAddress() {
        hideKeyboard()
        binding.apply {
            viewModel.saveAddress(
                cep = edtCep.text.toString(),
                street = edtStreet.text.toString(),
                number = edtNumber.text.toString(),
                complement = edtComplement.text.toString(),
                district = edtDistrict.text.toString()
            )
        }
    }
}