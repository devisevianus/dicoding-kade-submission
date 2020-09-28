package com.devis.foobatllapp.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Devis on 27/09/20
 */

data class TeamsMdl(
    @SerializedName("teams") val list_team: List<TeamMdl>
) : Serializable {

    data class TeamMdl(
        @SerializedName("idTeam")  val id_team: String,
        @SerializedName("strTeam") val team_name: String,
        @SerializedName("strStadium") val stadium_name: String,
        @SerializedName("strTeamBadge") val team_badge_url: String
    ) : Serializable

}
