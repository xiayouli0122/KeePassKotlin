package com.yuri.keepass.model


class HistoryFile() {

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