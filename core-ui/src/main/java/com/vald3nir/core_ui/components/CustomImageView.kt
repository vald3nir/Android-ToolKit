package com.vald3nir.core_ui.components

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.vald3nir.core_ui.R

class CustomImageView : AppCompatImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    fun showLoadingAnimation() {
        startAnimation(
            AnimationUtils.loadAnimation(
                context, R.anim.rotate_indefinitely
            )
        )
    }

    fun loadImageBase64(data: String?, placeholder: Int) {
//        try {
//            setImageBitmap(data.toBitmap())
//        } catch (e: Exception) {
//            e.printStackTrace()
//            loadImage(placeholder)
//        }
    }

    fun loadImage(uri: Uri, placeholder: Int) {
        Glide.with(context).load(uri)
            .placeholder(placeholder)
            .into(this)
    }

    fun loadImage(placeholder: Int) {
        Glide.with(context).load(placeholder).into(this)
    }
}