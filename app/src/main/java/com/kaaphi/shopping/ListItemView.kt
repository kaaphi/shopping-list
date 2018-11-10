package com.kaaphi.shopping

data class ListItemView(val item : ListItem, var checked : Boolean = false, var quantity : Int? = null) {
    fun label() : String {
        return if(quantity == null) item.name else "$quantity ${item.name}"
    }
}