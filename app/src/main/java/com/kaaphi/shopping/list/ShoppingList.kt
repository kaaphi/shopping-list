package com.kaaphi.shopping.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ShoppingList (val name : String, val items : MutableList<ListItemView> = mutableListOf()) : Parcelable {

}