package com.vald3nir.toolkit.data.dto

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Ignore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.util.UUID.randomUUID

open class BaseDTO(
    @Ignore val uid: String = randomUUID().toString()
) : Serializable {

    fun toJson(): String? {
        return Gson().toJson(this)
    }

    fun <T> fromJson(json: String?, classOfT: Class<T>): T {
        return Gson().fromJson(json, classOfT)
    }

    fun toMap(): Map<String, Any> {
        return convert()
    }

    private inline fun <I, reified O> I.convert(): O {
        val gson = Gson()
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<O>() {}.type)
    }
}

fun <T> baseDiffUtil(): DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return if (oldItem is BaseDTO && newItem is BaseDTO)
            oldItem.uid == newItem.uid
        else
            false
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}