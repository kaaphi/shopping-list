package com.kaaphi.shopping.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class ListItemView(val item : ListItem, var checked : Boolean = false, var quantity : Int? = null) : Parcelable, Serializable {
    fun label() : String {
        return if(quantity == null) item.name else "$quantity ${item.name}"
    }
}