package com.devis.foobatllapp.core.util

import com.devis.foobatllapp.core.util.DateUtil.DEFAULT_DATE_FORMAT
import com.devis.foobatllapp.core.util.DateUtil.MMM_DD_YYYY
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