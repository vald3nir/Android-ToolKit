package com.vald3nir.toolkit.componets.customviews

import android.R
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner

class CustomSpinner : AppCompatSpinner {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    var itemSelected = "null"

    fun setup(
        textColorItemSelected: Int,
        list: List<String>,
        onItemSelected: ((String) -> Unit)? = null
    ) {
        adapter = ArrayAdapter(context, R.layout.simple_spinner_item, list)
        onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                (parent?.getChildAt(0) as TextView?)?.setTextColor(textColorItemSelected)
                itemSelected = list[position]
                onItemSelected?.invoke(itemSelected)
            }
        }
    }
}