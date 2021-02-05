package com.devis.foobatllapp.data.source

import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.StatisticsMdl
import com.devis.foobatllapp.data.util.ResultState

interface MatchDataSource {

    suspend fun getEventDetailById(id: String): ResultState<EventsMdl>
    suspend fun getEventStatisticsById(id: String): ResultState<StatisticsMdl>
    suspend fun searchEventByClubName(event: String, season: String): ResultState<EventsMdl>

}