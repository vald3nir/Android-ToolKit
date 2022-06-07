package com.vald3nir.diskwater.common

import android.content.Intent
import android.provider.MediaStore
import com.vald3nir.toolkit.core.CoreFragment

open class BaseFragment : CoreFragment() {

    val REQUEST_IMAGE_CAPTURE = 1

    fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }
}