package com.devis.foobatllapp.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Devis on 22/09/20
 */

data class LeagueMdl(
    val leagueId: String,
    val leagueLogo: Int,
    val leagueName: String,
    val leagueDetail: String,
    val leagueBackground: Int
) : Serializable

data class ResponseListLeagueMdl(
    @SerializedName("leagues") val leagues: List<ResponseLeagueMdl>
) : Serializable

data class ResponseLeagueMdl(
    @SerializedName("idLeague") val league_id: String,
    @SerializedName("strLeague") val league_name: String,
    @SerializedName("strDescriptionEN") val league_description: String,
    @SerializedName("strBanner") val league_banner: String,
    @SerializedName("strBadge") val league_badge: String
) : Serializable