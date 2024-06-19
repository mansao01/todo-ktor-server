package com.mansao01.entity

import kotlinx.serialization.Serializable

@Serializable
data class ToDo(
    val id:Int,
    var title:String,
    var done:Boolean
)
