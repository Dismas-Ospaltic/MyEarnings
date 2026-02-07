package com.gw.myearnings.utils



import android.icu.text.SimpleDateFormat
import java.util.*

fun dateFormat(timestamp: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(Date(timestamp))
}