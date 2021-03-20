package ru.netology.testtaskax.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class Comment(
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String,
//    var _date: String? = null
) //{
//      var date: String
//        get() = _date ?: Calendar.getInstance().time.toString()
//    set(value) {
//        _date = value
//    }
//}
