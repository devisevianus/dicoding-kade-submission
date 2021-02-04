package com.devis.foobatllapp.core.model

/**
 * Created by Devis on 27/09/20
 */

class ErrorMdl(
    val status: Boolean = false,
    val http_code: Int? = null,
    val message: String? = null
)