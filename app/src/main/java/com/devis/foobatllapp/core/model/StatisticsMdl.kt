package com.devis.foobatllapp.core.model

import java.io.Serializable

data class StatisticsMdl(
    val eventstats: List<EventStatisticMdl>
) : Serializable

data class EventStatisticMdl(
    val idStatistic: String,
    val idEvent: String,
    val idApiFootball: String,
    val strEvent: String,
    val strStat: String,
    val intHome: String,
    val intAway: String
) : Serializable