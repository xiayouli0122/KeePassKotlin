package com.yuri.keepass.db

/**
 * Created by Yuri on 2018/4/26.
 */

class PwIconStandard : PwIcon {

    private var iconId : Int

    companion object {
        val FIRST = PwIconStandard(1)
        val TRASH_BIN = 43
        val FOLDER = 48
    }

    constructor(iconId: Int) {
        this.iconId = iconId
    }

    override fun isMetaStreamIcon(): Boolean {
        return iconId == 0
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + iconId
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other === null) {
            return false
        }

        if (javaClass != other.javaClass) {
            return false
        }

        val iconStandard = other as PwIconStandard

        return iconId == iconStandard.iconId
    }


}