package com.yuri.keepass

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.yuri.keepass.model.HistoryFile
import io.objectbox.BoxStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private var adapter:HistoryDBAdapter? = null

    private lateinit var boxStore: BoxStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_db_history.layoutManager = LinearLayoutManager(this)

        adapter = HistoryDBAdapter(this, null)
        rv_db_history.adapter = adapter

        val application = application as MyApplication
        boxStore = application.getBoxStore()

        getData()
    }

    private fun getData() {
        val boxFor = boxStore.boxFor(HistoryFile::class.java)
        val list:List<HistoryFile> = boxFor.query().build().find()
        Log.d(TAG, "size=" + list.size)
        adapter!!.setDataList(list)
    }

    fun onOpenDBClick(view: View) {
    }

    fun onCreateDBClick(view : View) {
        val intent = Intent()
        intent.setClass(this, CreateActivity::class.java)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            getData()
        }
    }

}
