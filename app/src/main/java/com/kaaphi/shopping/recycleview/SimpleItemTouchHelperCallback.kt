package com.kaaphi.shopping.recycleview

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
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

    override fun onMove(recyclerView: androidx.recyclerview.widget.RecyclerView, viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, target: androidx.recyclerview.widget.RecyclerView.ViewHolder): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemSwipe(viewHolder.adapterPosition)
    }
}