package com.yuri.keepass.utils

import android.content.Context
import com.yuri.xlog.XLog

/**
 * Created by Yuri on 2018/4/19.
 */
class LoadDbTask : RunnableOnFinish {

    constructor(context: Context, text : String, finishTask: OnFinishTask) : super(finishTask) {

    }

    override fun run() {
        // do something....
        XLog.d("do something")

        val text = ":adbsddgsddfsdfsdf?"

        val startTime = System.currentTimeMillis()
        XLog.d("starttime:" + startTime)
        var hash: String
        for (i in 0 until Integer.MAX_VALUE) {
            hash = TestUtils.SHA256(text + i)
            val ret = TestUtils.isValid(hash)

            if (i % 8 == 0) {
                mStatus.updateMessage("Loading...." + i)
            }

            if (ret) {
                XLog.d("i=" + i)
                XLog.d(hash)
                break
            }
        }
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        XLog.d("Duration:" + duration)

        finish(true, "Nothing is impossible")
    }
}