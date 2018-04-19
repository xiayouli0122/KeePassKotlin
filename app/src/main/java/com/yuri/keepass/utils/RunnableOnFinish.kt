package com.yuri.keepass.utils

/**
 * 开启一个线程执行任务，任务完成后，自动回调
 * Created by Yuri on 2018/4/16.
 */

abstract class RunnableOnFinish(finishTask: OnFinishTask) : Runnable {

    /**
     * 设置一个结束处理的标记任务，当任务结束完成后，调用finishTask去通知调用者
     */
    var mOnFinishTask: OnFinishTask = finishTask
    /**
     * 可以通过这个更新任务的实时状态
     */
    lateinit var mStatus: UpdateStatus

    /**
     * 任务完成
     * @param result 任务完成状态：true(成功)  false(失败)
     */
    protected fun finish(result: Boolean) {
        finish(result, "")
    }

    /**
     * 任务完成
     * @param result 任务完成状态：true(成功)  false(失败)
     * @param message 文本消息(完成之后需要传递的文本内容)
     */
    protected fun finish(result: Boolean, message: String) {
        mOnFinishTask.setResult(result, message)
        mOnFinishTask.run()
    }

    /**
     * 设置支持任务执行状态更新的方法
     */
    fun setStatus(status: UpdateStatus) {
        mStatus = status
    }

    /**
     * 等待具体的任务实现者去实现处理
     */
    abstract override fun run()

}