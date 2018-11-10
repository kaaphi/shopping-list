package com.kaaphi.shopping.additem

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.kaaphi.shopping.ListItem
import com.kaaphi.shopping.R
import android.support.v4.app.NavUtils



class AddItem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
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

        val intent = Intent()
        intent.putExtra("itemName", itemName)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
