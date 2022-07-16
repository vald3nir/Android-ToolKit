package com.vald3nir.core_ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import com.vald3nir.core_ui.R

class CustomSpinner : AppCompatSpinner {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    var itemSelected = "null"
    private var items = listOf<String>()

    fun setup(
        list: List<String>,
        onItemSelected: ((String) -> Unit)? = null,
        textColorItemSelected: Int = R.color.black,
        textLayout: Int = R.layout.custom_spinner_item

    ) {
        items = list
        adapter = ArrayAdapter(context, textLayout, list)
        onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                (parent?.getChildAt(0) as TextView?)?.setTextColor(
                    ContextCompat.getColor(
                        context,
                        textColorItemSelected
                    )
                )
                itemSelected = list[position]
                onItemSelected?.invoke(itemSelected)
            }
        }
    }

    fun setItemSelection(item: String?) {
        item?.let { setSelection(items.indexOf(it)) }
    }
}