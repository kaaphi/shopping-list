package com.kaaphi.shopping

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast

open class PersistenceAsyncTask<Params, Result>(
    val context: Context,
    val background : (Array<out Params>) -> Result
) : AsyncTask<Params, Any, PersistenceAsyncTask.PersistenceResult<Result>>() {

    override fun doInBackground(vararg params: Params): PersistenceResult<Result> {
        return try {
            PersistenceResult(background.invoke(params))
        } catch (th : Throwable) {
            Log.e("PersistenceAsyncTask", "failed to persist", th)
            PersistenceResult(null,false,th.message)
        }
    }

    override fun onPostExecute(result: PersistenceResult<Result>?) {
        if(result?.success == true && result?.result != null) {
            onResult(result.result)
        } else if (result?.success == false) {
            Toast.makeText(context, result.message ?: "Persistence failed", Toast.LENGTH_SHORT)
        }
    }

    open fun onResult(result : Result) {}

    class PersistenceResult<Result>(val result : Result? = null, val success : Boolean = true, val message : String? = null)


}