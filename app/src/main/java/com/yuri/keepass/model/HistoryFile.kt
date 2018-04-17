package com.yuri.keepass.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id


@Entity
class HistoryFile() {
    //一定要有一个ID
    @Id var id:Long = 0
    var name :String? = null
    var path:String? = null

    constructor(name:String, path:String) : this() {
        this.name = name
        this.path = path
    }

    override fun toString(): String {
        return "HistoryFile(name=$name, path=$path)"
    }


}