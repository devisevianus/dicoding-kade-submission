package com.devis.foobatllapp.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Devis on 27/09/20
 */

data class EventsMdl(
    @SerializedName("events") val events: List<EventMdl>
) : Serializable

data class EventMdl(
    @SerializedName("idEvent") val id_event: String,
    @SerializedName("strHomeTeam") val team_home: String,
    @SerializedName("strAwayTeam") val team_away: String,
    @SerializedName("intHomeScore") val home_score: Int,
    @SerializedName("intAwayScore") val away_score: Int,
    @SerializedName("dateEvent") val date_event: String
)