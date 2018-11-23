package com.kaaphi.shopping.list

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import java.io.Serializable

@Parcelize
data class ListItem(var name : String) : Parcelable, Serializable {
}