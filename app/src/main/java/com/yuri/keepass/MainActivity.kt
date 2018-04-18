package com.yuri.keepass

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.leon.lfilepickerlibrary.LFilePicker
import com.leon.lfilepickerlibrary.utils.Constant
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import com.yanzhenjie.permission.Rationale
import com.yuri.keepass.model.HistoryFile
import io.objectbox.BoxStore
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.InputStream

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

        requestPermission()
    }

    private fun getData() {
        val boxFor = boxStore.boxFor(HistoryFile::class.java)
        val list:List<HistoryFile> = boxFor.query().build().find()
        Log.d(TAG, "size=" + list.size)
        adapter!!.setDataList(list)
    }

    fun onOpenDBClick(view: View) {
        //打开文件选择
        LFilePicker().withActivity(this)
                .withRequestCode(101)
                .withTitle("选择kdbx文件")
                .withStartPath(Environment.getExternalStorageDirectory().absolutePath)
                .withChooseMode(true)
                .withMutilyMode(false)
                .withFileFilter(arrayOf("kdbx"))
                .start()
    }

    fun onCreateDBClick(view : View) {
        val intent = Intent()
        intent.setClass(this, CreateActivity::class.java)
        startActivityForResult(intent, 100)
    }

    /**
     * 申请存储权限
     */
    private fun requestPermission() {
        AndPermission.with(this)
                .permission(Permission.Group.STORAGE)
                .rationale(mRationale)
                .onGranted {
                    Log.d("Yuri", "权限授予成功")
                }
                .onDenied {
                    Log.d("Yuri", "权限授予失败")
                    //权限授予失败
                    if (AndPermission.hasAlwaysDeniedPermission(this, it)) {
                        //用户总是拒绝授予权限
                        MaterialDialog.Builder(this)
                                .title("提示")
                                .content("本应用需要使用到存储权限以打开本地的数据库文件")
                                .positiveText("去设置")
                                .negativeText("取消")
                                .cancelable(false)
                                .onPositive { dialog, which ->
                                    Log.d("Yuri", "OK")
                                    AndPermission.permissionSetting(this).execute()
                                    this.finish()
                                }
                                .onNegative { dialog, which ->
                                    Log.w("Yuri", "Fail")
                                    MainActivity@ this.finish()
                                }
                                .show()
                    } else {
                        MainActivity@ this.finish()
                    }
                }
                .start()
    }

    //当授权被拒绝时，在用户下次请求权限时的回调
    private val mRationale = Rationale { context, permissions, executor ->
        MaterialDialog.Builder(context)
                .title("提示")
                .content("本应用需要使用到存储权限以打开本地的数据库文件")
                .positiveText("去授权")
                .negativeText("取消")
                .cancelable(false)
                .onPositive { dialog, which ->
                    Log.d("Yuri", "OK")
                    executor.execute()
                }
                .onNegative { dialog, which ->
                    Log.w("Yuri", "Fail")
                    executor.cancel()
                    MainActivity@this.finish()
                }
                .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 &&resultCode == Activity.RESULT_OK) {
            getData()
        }

        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val list = data?.getStringArrayListExtra(Constant.RESULT_INFO)
            if (list != null) {
                val path = list.get(0)
                Log.d("Yuri", "path=" + path)

            }
        }
    }

}
