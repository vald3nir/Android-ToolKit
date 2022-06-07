package com.vald3nir.toolkit.componets.customviews

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.AttributeSet
import android.util.Base64
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.vald3nir.toolkit.R
import java.io.ByteArrayOutputStream

class CustomImageView : AppCompatImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    fun showLoadingAnimation() {
        startAnimation(
            AnimationUtils.loadAnimation(context, R.anim.rotate_indefinitely)
        )
    }

    fun loadImage(uri: Uri, placeholder: Int) {
        Glide.with(context).load(uri)
//            .centerCrop()
            .placeholder(placeholder)
            .into(this)
    }

    fun loadImage(placeholder: Int) {
        Glide.with(context).load(placeholder).into(this)
    }
}