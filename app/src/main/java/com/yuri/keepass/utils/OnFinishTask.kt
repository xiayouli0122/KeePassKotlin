package com.yuri.keepass.utils

import android.content.Context
import android.os.Handler
import android.widget.Toast

/**
 * 在一个任务完成后回调，支持多层任务回调
 * Created by Yuri on 2018/4/16.
 */

open class OnFinishTask() : Runnable {

    /**
     * 任务完成状态标记（成功还是失败）
     */
    protected var mSuccess: Boolean = false
    /**
     * 任务完成后需要给调用者传递的消息内容
     */
    protected var mMessage: String? = ""
    /**
     * Task执行完毕后的回调
     */
    protected var mOnFinish: OnFinishTask? = null
    /**
     * Handler 用于子线程跟主线程之间转换
     */
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
