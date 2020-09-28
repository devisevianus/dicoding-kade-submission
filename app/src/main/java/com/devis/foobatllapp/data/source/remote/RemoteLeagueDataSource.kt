package com.devis.foobatllapp.data.source.remote

import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.ResultsMdl
import com.devis.foobatllapp.core.model.TeamsMdl
import com.devis.foobatllapp.data.source.LeagueDataSource
import com.devis.foobatllapp.data.util.ResultState
import com.devis.foobatllapp.data.util.fetchState
import com.devis.foobatllapp.data.util.handleError

/**
 * Created by Devis on 27/09/20
 */

class RemoteLeagueDataSource(
    private val apiService: ApiService
) : LeagueDataSource {

    override suspend fun getListTeamsByLeague(league: String): ResultState<TeamsMdl> {
        return fetchState {
            val response = apiService.getListTeamsByLeague(league)
            if (response.isSuccessful) {
                ResultState.Success(response.body())
            } else {
                ResultState.Error(response.handleError().message)
            }
        }
    }

    override suspend fun getLastFiveMatch(id: String): ResultState<ResultsMdl> {
        return fetchState {
            val response = apiService.getLastFiveTeamMatch(id)
            if (response.isSuccessful) {
                ResultState.Success(response.body())
            } else {
                ResultState.Error(response.handleError().message)
            }
        }
    }

    override suspend fun getLastLeagueMatch(id: String): ResultState<EventsMdl> {
        return fetchState {
            val response = apiService.getLastLeagueMatch(id)
            if (response.isSuccessful) {
                ResultState.Success(response.body())
            } else {
                ResultState.Error(response.handleError().message)
            }
        }
    }

}