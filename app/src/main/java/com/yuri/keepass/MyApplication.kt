package com.yuri.keepass

import android.app.Application
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
    }

    fun getBoxStore() : BoxStore{
        return boxStore!!
    }

}