package com.kaaphi.shopping.additem

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.kaaphi.shopping.R
import com.kaaphi.shopping.list.ListItem

class AddItemListAdapter(
    private val itemSelected : (item : ListItem) -> Unit
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<AddItemListAdapter.MyViewHolder>() {

    private val items = emptyList<ListItem>().toMutableList()/*CharRange('A','Z')
        .shuffled()
        .mapIndexed { idx, ch -> ListItem(ch.toString(), (idx + 1)*100)}
        .toMutableList()*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_view, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = "${items[position].name} (${items[position].rank})"

        holder.view.setOnLongClickListener {
            AlertDialog.Builder(holder.view.context)
                .setTitle("Delete Item?")
                .setMessage("Delete \"${items[position].name}\"?")
                .setPositiveButton("Yes") { _ : DialogInterface, _ : Int ->
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("No") { dialog: DialogInterface, _: Int ->
                    dialog.cancel()
                }.show()

            true
        }

        holder.view.setOnClickListener {
            itemSelected.invoke(items[position])
        }
    }

    class MyViewHolder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val textView : TextView = view.findViewById<TextView>(R.id.listItemTextView)
    }
}