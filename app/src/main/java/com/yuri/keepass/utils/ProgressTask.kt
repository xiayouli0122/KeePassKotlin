package com.yuri.keepass.utils

import android.content.Context
import android.os.Handler
import com.afollestad.materialdialogs.MaterialDialog

/**
 * 带加载进度的Task
 * 支持单独开一个线程执行一个Task
 * Created by Yuri on 2018/4/19.
 */

class ProgressTask(context: Context, task: RunnableOnFinish, message : String) : Runnable {
    private var mTask : RunnableOnFinish = task
    private var handler : Handler = Handler()
    private var progressDialog: MaterialDialog = MaterialDialog.Builder(context)
            .content(message)
            .cancelable(false)
            .progress(true, 0)
            .build()

    init {
        mTask.mStatus = UpdateStatus(handler, progressDialog)
        mTask.mOnFinishTask = AfterTask(mTask.mOnFinishTask, handler)
    }

    override fun run() {
        progressDialog.show()
        //start thread run task
        Thread(mTask).start()
    }

    private inner class AfterTask(finish: OnFinishTask, handler: Handler) : OnFinishTask(finish, handler) {

        override fun run() {
            super.run()
            // Remove the progress dialog
            mHandler!!.post(CloseProcessDialog())
        }

    }

    private inner class CloseProcessDialog : Runnable {
        override fun run() {
            progressDialog.dismiss()
        }

    }

}