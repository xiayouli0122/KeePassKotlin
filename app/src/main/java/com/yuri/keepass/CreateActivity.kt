package com.yuri.keepass

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yuri.keepass.extension.showToast
import com.yuri.keepass.model.HistoryFile
import io.objectbox.BoxStore
import kotlinx.android.synthetic.main.activity_create.*
import java.io.File

/**
 * 创建数据库界面
 */
class CreateActivity : AppCompatActivity() {

    private val TAG = CreateActivity::class.java.simpleName

    //数据库文件名称
    private lateinit var mFileName : String

    private lateinit var boxStore: BoxStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val application = application as MyApplication
        boxStore = application.getBoxStore()
    }

    //下一步
    fun onNextButtonClick(view:View) {
        val text = et_db_name_input.text.trim().toString()
        if (text.isEmpty()) {
            showToast("文件名不能为空")
            return
        }
        mFileName = text

        create_group1.visibility = View.GONE
        create_group2.visibility = View.VISIBLE
    }

    //上一步
    fun onPreButtonClick(view: View) {
        create_group1.visibility = View.VISIBLE
        create_group2.visibility = View.GONE
    }

    //完成
    fun onCompleteButtonClick(view:View) {
        val password1 = et_pw_set.text.trim().toString()
        val password2 = et_pw_set_confirm.text.trim().toString()

        if (password1.isEmpty() || password2.isEmpty()) {
            showToast("密码不能为空")
            return
        }

        createDBFile()

    }

    fun onOpenFileClick(view: View) {

    }

    /**
     * 创建数据库文件
     */
    private fun createDBFile() {
        val absolutePath = applicationContext.getExternalFilesDir(null).absolutePath + "/db"
        val dirFile = File(absolutePath)
        if (!dirFile.exists()) {
            val mkdirs = dirFile.mkdirs()
            if (!mkdirs) {
                Log.e(TAG, "mkdirs error")
                return
            }
        }

        val dbPath = "$absolutePath/$mFileName.kdbx"
        val dbFile = File(dbPath)
        if (!dbFile.exists()) {
            val createNewFile = dbFile.createNewFile()
            if (!createNewFile) {
                Log.e(TAG, "createNewFile error")
                return
            }
        }

        val history = HistoryFile()
        history.name = mFileName
        history.path = dbPath
        val boxFor = boxStore.boxFor(HistoryFile::class.java)
        boxFor.put(history)

        Log.d(TAG, history.toString())
    }
}
