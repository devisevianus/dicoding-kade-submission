package com.devis.foobatllapp.core.model

import java.io.Serializable

/**
 * Created by Devis on 22/09/20
 */

data class LeagueMdl(
    val leagueName: String,
    val leagueLogo: ByteArray
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LeagueMdl

        if (leagueName != other.leagueName) return false
        if (!leagueLogo.contentEquals(other.leagueLogo)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = leagueName.hashCode()
        result = 31 * result + leagueLogo.contentHashCode()
        return result
    }
}