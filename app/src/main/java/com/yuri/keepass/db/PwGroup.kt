package com.yuri.keepass.db

import java.util.ArrayList

/**
 * Created by Yuri on 2018/4/26.
 */
abstract class PwGroup {
    var childGroups: List<PwGroup> = ArrayList()
    //Entry
    var name : String = ""
    var icon: PwIconStandard = null
}