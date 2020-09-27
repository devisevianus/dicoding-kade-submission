package com.devis.foobatllapp.core.model

import java.io.Serializable

/**
 * Created by Devis on 22/09/20
 */

data class LeagueMdl(
    val leagueLogo: Int,
    val leagueName: String,
    val leagueDetail: String,
    val leagueBackground: Int
) : Serializable