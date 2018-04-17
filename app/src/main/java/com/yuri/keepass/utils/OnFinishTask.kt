package com.yuri.keepass.utils

import android.content.Context
import android.os.Handler
import android.widget.Toast

/**
 * 在一个任务完成后回调
 * Created by Yuri on 2018/4/16.
 */

class OnFinishTask() : Runnable {

    protected var mSuccess: Boolean = false
    protected var mMessage: String? = ""

    protected var mOnFinish: OnFinishTask? = null
    protected var mHandler: Handler? = null

    constructor(handler: Handler) : this() {
        mHandler = handler
        mOnFinish = null
    }

    constructor(finish:OnFinishTask) : this() {
        mHandler = null
        mOnFinish = finish
    }

    constructor(finish:OnFinishTask, handler: Handler) : this() {
        mOnFinish = finish
        mHandler = handler
    }

    fun setResult(success: Boolean, message: String) {
        mSuccess = success
        mMessage = message
    }

    fun setResult(success: Boolean) {
        mSuccess = success
    }

    override fun run() {
        if (mOnFinish != null) {
            mOnFinish!!.setResult(mSuccess, mMessage!!)

            if (mHandler != null) {
                mHandler!!.post(mOnFinish)
            } else {
                mOnFinish!!.run()
            }
        }
    }

    protected fun displayMessage(ctx: Context) {
        if (mMessage!= null && mMessage!!.length > 0) {
            Toast.makeText(ctx, mMessage, Toast.LENGTH_LONG).show()
        }
    }
}
