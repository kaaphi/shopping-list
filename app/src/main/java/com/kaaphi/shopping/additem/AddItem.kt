package com.kaaphi.shopping.additem

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.kaaphi.shopping.R
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Button
import com.kaaphi.shopping.list.ListItem

class AddItem : AppCompatActivity() {

    companion object {
        const val ITEM_KEY = "item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val editText = findViewById<EditText>(R.id.itemName)

        editText.addTextChangedListener(ButtonEnableTextWatcher())

        val viewManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        val viewAdapter = AddItemListAdapter(this::itemSelected)

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.item_list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        editText.requestFocus()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun addItem(view : View) {
        val editText = findViewById<EditText>(R.id.itemName)
        val itemName = editText.text.toString()

        itemSelected(ListItem(itemName))
    }

    private fun itemSelected(item : ListItem) {
        val intent = Intent()

        intent.putExtra(ITEM_KEY, item as Parcelable)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    inner class ButtonEnableTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val button = findViewById<Button>(R.id.addItem)

            button.isEnabled = !s.isNullOrEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //noop
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //noop
        }
    }
}
