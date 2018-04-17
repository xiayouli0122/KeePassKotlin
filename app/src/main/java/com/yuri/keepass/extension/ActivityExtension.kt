package com.yuri.keepass.extension

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


fun Context.showToast(msg:String) {
    Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
}

//扩展属性
var TextView.leftMargin:Int
    get():Int {
        return (layoutParams as ViewGroup.MarginLayoutParams).leftMargin
    }
    set(value) {
        (layoutParams as ViewGroup.MarginLayoutParams).leftMargin=value
    }

//var Activity.TAG : String{
//    get():Int{
//
//    }
//}
