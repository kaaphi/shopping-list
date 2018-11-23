package com.kaaphi.shopping.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
class ShoppingList (val name : String, val items : MutableList<ListItemView> = mutableListOf()) : Parcelable, Serializable {
    fun clearCheckedItems() {
        val checkedItems = items
            .mapIndexed(::IndexedValue)
            .filter { v -> v.value.checked }

        items.removeAll(checkedItems.map { item -> item.value })
    }
}