package com.kaaphi.shopping.recycleview

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.kaaphi.shopping.recycleview.ItemTouchHelperAdapter

class SimpleItemTouchHelperCallback(
    private val adapter : ItemTouchHelperAdapter,
    var enableLongPressDrag : Boolean = false,
    var enableSwipe : Boolean = true,
    dragFlags : Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    swipeFlags : Int = ItemTouchHelper.START or ItemTouchHelper.END
) : ItemTouchHelper.SimpleCallback(dragFlags, swipeFlags) {

    override fun isLongPressDragEnabled(): Boolean {
        return enableLongPressDrag
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return enableSwipe
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemSwipe(viewHolder.adapterPosition)
    }
}