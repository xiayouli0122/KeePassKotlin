package com.yuri.keepass.db

import java.util.*

/**
 * 第四版本GroupId，其实目前基本不会用到其他版本了，直接使用第四版本就可以
 * Created by Yuri on 2018/4/26.
 */

class PwGroupIdV4 : PwGroupId {
    private var uuid : UUID

    constructor(uuid: UUID) {
        this.uuid = uuid
    }

    override fun equals(other: Any?): Boolean {
        if (other !is PwGroupIdV4) {
            return  false
        }
        return this.uuid == other.uuid
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }

    fun getId() : UUID {
        return uuid
    }

}