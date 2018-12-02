package com.kaaphi.shopping

import androidx.recyclerview.widget.RecyclerView

open class AllChangesAdapterDataObserver : androidx.recyclerview.widget.RecyclerView.AdapterDataObserver() {
    override fun onChanged() {

    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        onChanged()
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        onChanged()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        onChanged()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        onChanged()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        onChanged()
    }
}