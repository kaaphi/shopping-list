package com.kaaphi.shopping

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kaaphi.shopping.list.ListItemView
import java.util.*

class MyAdapter(private val myDataset: MutableList<ListItemView>, private val dragListener: StartDragListener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(), ItemTouchHelperAdapter {

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(myDataset, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwipe(position: Int) {
        myDataset.removeAt(position)
        notifyItemRemoved(position)
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView : CheckBox = view.findViewById<CheckBox>(R.id.textView)
        val handle : ImageView = view.findViewById<ImageView>(R.id.handle)
        val editButton : ImageButton = view.findViewById<ImageButton>(R.id.editItem)
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_view, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
        val itemView = myDataset[position];

        holder.textView.text = itemView.label().toString()
        holder.textView.isChecked = itemView.checked
        holder.textView.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                buttonView.paintFlags = buttonView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                buttonView.paintFlags = buttonView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            itemView.checked = isChecked
        }
        holder.editButton.setOnClickListener {
            val editValue = EditText(holder.textView.context)
            editValue.inputType = InputType.TYPE_CLASS_NUMBER
            AlertDialog.Builder(holder.textView.context)
                .setTitle("Enter Quantity")
                .setView(editValue)
                .setPositiveButton("OK") { _ : DialogInterface, _ : Int ->
                    val quantity = editValue.text.toString()
                    itemView.quantity = if(quantity.isNotEmpty()) quantity.toInt() else null
                    holder.textView.text = itemView.label().toString()
                }
                .setNegativeButton("Cancel") { dialog: DialogInterface, _: Int ->
                    dialog.cancel()
                }
                .show()
        }

        holder.handle.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                dragListener.requestDrag(holder);
            }
           true
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}