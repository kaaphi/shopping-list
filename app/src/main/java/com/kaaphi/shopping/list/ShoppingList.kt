package com.kaaphi.shopping.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
class ShoppingList (
    val name : String,
    val items : MutableList<ListItemView> = mutableListOf()
) : Parcelable {
    fun clearCheckedItems() {
        val checkedItems = items
            .mapIndexed(::IndexedValue)
            .filter { v -> v.value.checked }

        items.removeAll(checkedItems.map { item -> item.value })
    }
}