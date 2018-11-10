package com.kaaphi.shopping

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemSwipe(position: Int)
}