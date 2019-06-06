package com.yuri.keepass.db

/**
 * Item Icon
 * Created by Yuri on 2018/4/26.
 */
abstract class PwIcon {

    //Kotlin方法默认是final不可被重载，想要被子类重载就要在方法名加open
    open fun isMetaStreamIcon(): Boolean {
        return false
    }

    fun writeBytes() {

    }
}