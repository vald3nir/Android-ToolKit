package com.vald3nir.toolkit.componets.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.vald3nir.toolkit.databinding.CustomButtonBinding

class CustomButton : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        orientation = VERTICAL
    }

    private val binding = CustomButtonBinding.inflate(LayoutInflater.from(context), this, true)

    fun setTitle(title: String?) {
        binding.button.text = title
    }

    fun setTitleColor(color: Int) {
        binding.button.apply {
            setTextColor(ContextCompat.getColor(context, color))
        }
    }

    fun setBackgroundDrawable(drawable: Int) {
        binding.apply {
            background = ContextCompat.getDrawable(context, drawable)
        }
    }

    fun setOnClickListener(listener: () -> Unit) {
        binding.button.setOnClickListener { listener.invoke() }
    }

    fun showLoading(show: Boolean) {
        binding.apply {
            button.isVisible = !show
            loading.isVisible = show
        }
    }
}