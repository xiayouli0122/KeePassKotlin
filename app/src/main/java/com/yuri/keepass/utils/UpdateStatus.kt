package com.yuri.keepass.utils

import android.os.Handler
import com.afollestad.materialdialogs.MaterialDialog

/**
 * 更新任务状态
 * Created by Yuri on 2018/4/16.
 */
class UpdateStatus(handler: Handler, progressDialog: MaterialDialog) {
    private var mProgressDialog : MaterialDialog = progressDialog
    private var mHandler: Handler = handler

    fun updateMessage(message:String) {
        mHandler.post(UpdateMessage(message))
    }

    private inner class UpdateMessage(msg: String) : Runnable {
        private var message:String = msg

        override fun run() {
            mProgressDialog.setContent(message)
        }
    }

}