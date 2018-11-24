package com.kaaphi.shopping.list

import android.content.Context
import android.util.Log
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.cbor.CBOR
import kotlinx.serialization.dump
import kotlinx.serialization.load
import java.io.*

@ImplicitReflectionSerializer
object ShoppingListPersister {
    fun save(context : Context, list : ShoppingList) {
        val shoppingListFile = File(listDir(context), listFileName(list.name))

        val bytes = CBOR.dump(list)

        val output = FileOutputStream(shoppingListFile)
        output.use {
            output.write(bytes)
        }
    }

    fun loadList(context : Context, listName : String) : ShoppingList {
        val shoppingListFile = File(listDir(context), listFileName(listName))
        return if(shoppingListFile.exists()) {
            try {
                loadList(shoppingListFile)
            } catch (th : Throwable) {
                Log.e("persist", "Failed to load list $shoppingListFile", th)
                ShoppingList(listName)
            }
        } else {
            ShoppingList(listName)
        }
    }

    fun loadAll(context : Context) : List<ShoppingList> {
        return listDir(context).listFiles()
            .map(this::loadList)
    }

    private fun loadList(listFile : File) : ShoppingList {
        val input = FileInputStream(listFile)
        input.use {
            return CBOR.load(input.readBytes())
        }
    }

    private fun listDir(context : Context) : File {
        return context.applicationContext.getDir("lists", Context.MODE_PRIVATE)
    }

    private fun listFileName(listName : String) : String {
        return listName.replace("\\W+", "")
    }
}