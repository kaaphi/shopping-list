package com.kaaphi.shopping.recycleview

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemSwipe(position: Int)
}