package com.kaaphi.shopping

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import com.kaaphi.shopping.additem.AddItem
import com.kaaphi.shopping.list.ListItem
import com.kaaphi.shopping.list.ListItemView

const val ADD_ITEM_REQUEST  = 0;

class MainActivity : AppCompatActivity(), StartDragListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var touchHelper : ItemTouchHelper
    private val myDataset : MutableList<ListItemView> = IntRange(1,5).map { idx -> "Item $idx" }
        .map { name -> ListItem(name) }
        .map{ item -> ListItemView(item) }
        .toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(myDataset, this)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        val callback = SimpleItemTouchHelperCallback(viewAdapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    fun clearChecked(view : View) {
        val checkedItems = myDataset
            .mapIndexed(::IndexedValue)
            .filter { v -> v.value.checked }

        myDataset.removeAll(checkedItems.map { item -> item.value })

        viewAdapter.notifyDataSetChanged()
    }

    fun addItem(view : View) {
        val intent = Intent(this, AddItem::class.java)
        startActivityForResult(intent, ADD_ITEM_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if((requestCode == ADD_ITEM_REQUEST) and (resultCode == Activity.RESULT_OK)) {
            val item : ListItem = data!!.getParcelableExtra(AddItem.ITEM_KEY)
            myDataset.add(ListItemView(item))
            viewAdapter.notifyItemInserted(myDataset.size)
        }
    }

    override fun requestDrag(viewHolder: RecyclerView.ViewHolder) {
        touchHelper.startDrag(viewHolder)
    }
}
