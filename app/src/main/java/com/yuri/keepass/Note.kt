package com.yuri.keepass

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Yuri on 2018/4/13.
 */


@Entity
data class Note(
    @Id var id: Long = 0,
    val text: String = ""
)
