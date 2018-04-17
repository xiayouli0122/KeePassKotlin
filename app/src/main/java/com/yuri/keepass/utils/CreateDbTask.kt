package com.yuri.keepass.utils

import android.content.Context

/**
 * 创建数据库
 * Created by Yuri on 2018/4/17.
 */
class CreateDbTask(finishTask: OnFinishTask) : RunnableOnFinish(finishTask) {

    private lateinit var mContext : Context
    private lateinit var mFileName : String

    constructor(context: Context, fileName:String, finishTask: OnFinishTask):this(finishTask) {
        this.mContext = context
        this.mFileName = fileName
    }

    override fun run() {

    }


}