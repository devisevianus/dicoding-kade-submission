package com.devis.foobatllapp.data.source.remote

import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.StatisticsMdl
import com.devis.foobatllapp.data.source.MatchDataSource
import com.devis.foobatllapp.data.util.ResultState
import com.devis.foobatllapp.data.util.fetchState
import com.devis.foobatllapp.data.util.handleError

class RemoteMatchDataSource(
    private val apiService: MatchService
) : MatchDataSource {

    override suspend fun getEventDetailById(id: String): ResultState<EventsMdl> {
        return fetchState {
            val result = apiService.getEventDetailById(id)
            if (result.isSuccessful) {
                ResultState.Success(result.body())
            } else {
                ResultState.Error(result.handleError().message)
            }
        }
    }

    override suspend fun getEventStatisticsById(id: String): ResultState<StatisticsMdl> {
        return fetchState {
            val result = apiService.getEventStatisticsById(id)
            if (result.isSuccessful) {
                ResultState.Success(result.body())
            } else {
                ResultState.Error(result.handleError().message)
            }
        }
    }

    override suspend fun searchEventByClubName(event: String, season: String): ResultState<EventsMdl> {
        return fetchState {
            val result = apiService.searchEventByClubName(event, season)
            if (result.isSuccessful) {
                ResultState.Success(result.body())
            } else {
                ResultState.Error(result.handleError().message)
            }
        }
    }

}