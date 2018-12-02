package com.kaaphi.shopping.recycleview

import androidx.recyclerview.widget.RecyclerView

interface StartDragListener {
    fun requestDrag(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder)
}