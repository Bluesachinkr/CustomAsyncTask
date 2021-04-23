package com.example.customasynctask

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val worker = Worker(this).execute("")
    }

    class Worker(context: Context) : CustomAsyncTask<String, String, String>() {
        private val context = context;
        private var dialog: ProgressDialog? = null
        override fun onPreExecute() {
            dialog = ProgressDialog(context)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                dialog?.create()
                dialog?.show()
            }
        }

        override fun doInBackground(vararg s: String?): String {
            var index = 0
            while (index < 20000) {
                index += 1
            }
            return ""
        }

        override fun onPostExecute(u: String?) {
            var index = 0
            while (index < 20000) {
                dialog?.setMessage("onPostExecute")
                index += 1
            }
        }
    }
}