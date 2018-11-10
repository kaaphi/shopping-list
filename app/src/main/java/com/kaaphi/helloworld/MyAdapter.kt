package com.kaaphi.helloworld

import android.annotation.SuppressLint
import android.graphics.Paint
import android.support.v4.view.MotionEventCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

class MyAdapter(private val myDataset: MutableList<String>, private val dragListener: StartDragListener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(), ItemTouchHelperAdapter {

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(myDataset, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        myDataset.removeAt(position)
        notifyItemRemoved(position)
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView : TextView = view.findViewById<TextView>(R.id.textView)
        val handle : ImageView = view.findViewById<ImageView>(R.id.handle)
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_view, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        val checkBox = view.findViewById<CheckBox>(R.id.textView)
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                buttonView.paintFlags = buttonView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                buttonView.paintFlags = buttonView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
        holder.textView.text = myDataset[position]

        holder.handle.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                dragListener.requestDrag(holder);
            }
           true
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}