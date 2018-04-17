package com.yuri.keepass.utils

/**
 * 开启一个线程执行任务，任务完成后，自动回调
 * Created by Yuri on 2018/4/16.
 */

abstract class RunnableOnFinish(finishTask: OnFinishTask) : Runnable {

    var mOnFinishTask: OnFinishTask = finishTask
    lateinit var mStatus: UpdateStatus

    protected fun finish(result: Boolean, message: String) {
        mOnFinishTask.setResult(result, message)
        mOnFinishTask.run()
    }

    protected fun finish(result: Boolean) {
        mOnFinishTask.setResult(result)
        mOnFinishTask.run()
    }

    fun setStatus(status: UpdateStatus) {
        mStatus = status
    }

}