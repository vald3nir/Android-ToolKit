package com.vald3nir.toolkit.componets.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.vald3nir.toolkit.R
import com.vald3nir.toolkit.databinding.CustomButtonBinding

class CustomButton : LinearLayout {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initAttrs(attrs)
    }

    init {
        orientation = VERTICAL
    }

    private val binding = CustomButtonBinding.inflate(LayoutInflater.from(context), this, true)

    @SuppressLint("CustomViewStyleable")
    private fun initAttrs(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButtonComponent)
        setTitle(
            typedArray.getString(
                R.styleable.CustomButtonComponent_title
            )
        )
        setTitleColor(
            typedArray.getColor(
                R.styleable.CustomButtonComponent_title_color,
                ContextCompat.getColor(context, R.color.black)
            )
        )
        setColorBackground(
            typedArray.getColor(
                R.styleable.CustomButtonComponent_background_color,
                ContextCompat.getColor(context, R.color.white)
            )
        )
        typedArray.recycle()
    }

    fun setTitle(title: String?) {
        binding.button.text = title
    }

    fun setTitleColor(color: Int) {
        binding.button.apply {
            setTextColor(ContextCompat.getColor(context, color))
        }
    }

    fun setColorBackground(color: Int) {
        binding.apply {
            setBackgroundColor(ContextCompat.getColor(context, color))
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