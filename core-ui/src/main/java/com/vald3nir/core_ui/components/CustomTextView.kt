package com.vald3nir.core_ui.components

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.vald3nir.core_ui.R


open class LabelView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        init(14F)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(14F)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(14F)
    }
}

class TitleView : LabelView {

    constructor(context: Context) : super(context) {
        init(18F)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(18F)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(18F)
    }
}

class BigTitleView : LabelView {

    constructor(context: Context) : super(context) {
        init(22F)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(22F)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(22F)
    }
}

private fun LabelView.init(textSize: Float) {
    setTextColor(ContextCompat.getColor(context, R.color.white))
    setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
}