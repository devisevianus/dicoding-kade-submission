package com.devis.foobatllapp.data.source

import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.ResultsMdl
import com.devis.foobatllapp.core.model.TeamsMdl
import com.devis.foobatllapp.data.util.ResultState

/**
 * Created by Devis on 27/09/20
 */

interface LeagueDataSource {

    suspend fun getListTeamsByLeague(league: String): ResultState<TeamsMdl>

    suspend fun getLastFiveMatch(id: String): ResultState<ResultsMdl>

    suspend fun getLastLeagueMatch(id: String): ResultState<EventsMdl>

    suspend fun getNextLeagueMatch(id: String): ResultState<EventsMdl>

}