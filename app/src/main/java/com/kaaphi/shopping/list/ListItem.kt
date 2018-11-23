package com.kaaphi.shopping.list

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class ListItem(var name : String) : Parcelable {
}