package com.devis.foobatllapp.core.util

import com.devis.foobatllapp.core.util.DateUtil.DEFAULT_DATE_FORMAT
import com.devis.foobatllapp.core.util.DateUtil.MMM_DD_YYYY
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Devis on 28/09/20
 */

object DateUtil {
    const val MMM_DD_YYYY = "MMM, dd yyyy"
    const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
}

fun String.convertDate(): String {
    var sdf = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault())
    val date = sdf.parse(this)
    sdf = SimpleDateFormat(MMM_DD_YYYY, Locale.getDefault())
    return sdf.format(date!!)
}

fun String.convertToJakartaTimeZone(): String {
    val timezone = TimeZone.getTimeZone("Asia/Jakarta")
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date = sdf.apply {
        timeZone = TimeZone.getTimeZone("GMT+0")
    }.parse(this)
    val dateFormatter: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return dateFormatter.apply {
        timeZone = timezone
    }.format(date!!)
}