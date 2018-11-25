package com.kaaphi.shopping.recycleview

import android.support.v7.widget.RecyclerView

interface StartDragListener {
    fun requestDrag(viewHolder: RecyclerView.ViewHolder)
}