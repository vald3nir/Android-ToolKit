package com.vald3nir.core_ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.vald3nir.core_ui.databinding.CustomButtonBinding

open class CustomButton : LinearLayout {

    constructor(context: Context?) : super(context) {
        initComponent()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initComponent()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initComponent()
    }

    private fun initComponent() {
        orientation = VERTICAL
        setBackgroundResource(0)
    }

    val binding by lazy {
        CustomButtonBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    protected fun setup(
        title: String?,
        titleColor: Int?,
        rootDrawable: Int?,
        clickListener: () -> Unit,
    ) {
        binding.apply {
            root.apply {
                rootDrawable?.let { setBackgroundResource(it) }
            }
            button.apply {
                text = title
                titleColor?.let {
                    setTextColor(ContextCompat.getColor(context, it))
                }
                setOnClickListener { clickListener.invoke() }
            }
        }
    }

    fun showLoading(show: Boolean) {
        binding.apply {
            button.isVisible = !show
            loading.isVisible = show
        }
    }
}