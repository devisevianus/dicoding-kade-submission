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
    @SerializedName("intHomeScore") val home_score: Int?,
    @SerializedName("intAwayScore") val away_score: Int?,
    @SerializedName("dateEvent") val date_event: String,
    @SerializedName("strTimestamp") val str_timestamp: String,
    @SerializedName("strTime") val str_time: String,
    @SerializedName("idHomeTeam") val id_home_team: String,
    @SerializedName("idAwayTeam") val id_away_team: String,
    @SerializedName("strHomeRedCards") val home_red_cards: String?,
    @SerializedName("strHomeYellowCards") val home_yellow_cards: String?,
    @SerializedName("strHomeGoalDetails") val home_goal_details: String?,
    @SerializedName("strAwayRedCards") val away_red_cards: String?,
    @SerializedName("strAwayYellowCards") val away_yellow_cards: String?,
    @SerializedName("strAwayGoalDetails") val away_goal_details: String?
) : Serializable

data class CustomEventMdl(
    val eventSide: String,
    val eventType: String,
    val eventValue: String,
    val homeScore: Int = 0,
    val awayScore: Int = 0
)