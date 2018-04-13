package com.yuri.keepass

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yuri.keepass.extension.showToast
import com.yuri.keepass.model.HistoryFile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter:HistoryDBAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_db_history.layoutManager = LinearLayoutManager(this)

        adapter = HistoryDBAdapter(this, null)
        rv_db_history.adapter = adapter

        getData()
    }

    fun getData() {
        val list = ArrayList<HistoryFile>()
        var historyFile = HistoryFile("AAAA", "/Scard/ddd/sss.kdbx")
        list.add(historyFile)

        historyFile = HistoryFile()
        historyFile.name = "ZBBBB"
        historyFile.path = "/sdfsd/sdsdd/ssddddddd"
        list.add(historyFile)

        adapter!!.setDataList(list)
    }

    fun onOpenDBClick(view: View) {
        println(">>>onOpenDBClick")

        showToast("打开数据库")
    }

    fun onCreateDBClick(view : View) {
        println(">>>onCreateDBClick")

        showToast("创建数据库")
    }



}
