package com.yuri.keepass

import android.app.Application
import com.yuri.xlog.Settings
import com.yuri.xlog.XLog
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

class MyApplication : Application() {

    private var boxStore: BoxStore? = null

    override fun onCreate() {
        super.onCreate()

        boxStore = MyObjectBox.builder().androidContext(this).build()

        if (BuildConfig.DEBUG) {
            AndroidObjectBrowser(boxStore).start(this)
        }

        XLog.initialize(Settings.getInstance().setAppTag("Yuri"))
    }

    fun getBoxStore() : BoxStore{
        return boxStore!!
    }

}