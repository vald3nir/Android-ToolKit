package com.vald3nir.toolkit.componets.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.vald3nir.toolkit.databinding.CustomButtonBinding

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

    protected val binding = CustomButtonBinding.inflate(LayoutInflater.from(context), this, true)

    fun setButtonTitle(title: String?) {
        binding.button.text = title
    }

    fun setButtonTitle(title: Int) {
        binding.button.apply {
            text = context.getText(title)
        }
    }

    fun setButtonTitleColor(color: Int) {
        binding.button.apply {
            setTextColor(ContextCompat.getColor(context, color))
        }
    }

    fun setRootDrawable(drawable: Int) {
        binding.root.apply {
            setBackgroundResource(drawable)
        }
    }

    fun setButtonClickListener(listener: () -> Unit) {
        binding.button.setOnClickListener { listener.invoke() }
    }

    fun showLoading(show: Boolean) {
        binding.apply {
            button.isVisible = !show
            loading.isVisible = show
        }
    }
}