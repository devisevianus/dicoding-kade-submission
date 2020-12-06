package com.devis.foobatllapp.data.repository

import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.ResultsMdl
import com.devis.foobatllapp.core.model.TeamsMdl
import com.devis.foobatllapp.data.source.remote.RemoteLeagueDataSource
import com.devis.foobatllapp.data.util.ResultState

/**
 * Created by Devis on 27/09/20
 */

interface LeagueRepository {
    suspend fun getListTeamsByLeague(league: String): ResultState<TeamsMdl>
    suspend fun getLastFiveMatch(id: String): ResultState<ResultsMdl>
    suspend fun getLastLeagueMatch(id: String): ResultState<EventsMdl>
    suspend fun getNextLeagueMatch(id: String): ResultState<EventsMdl>

    class LeagueRepositoryImpl(
        private val remoteLeagueDataSource: RemoteLeagueDataSource
    ) : LeagueRepository {

        override suspend fun getListTeamsByLeague(league: String): ResultState<TeamsMdl> {
            return remoteLeagueDataSource.getListTeamsByLeague(league)
        }

        override suspend fun getLastFiveMatch(id: String): ResultState<ResultsMdl> {
            return remoteLeagueDataSource.getLastFiveMatch(id)
        }

        override suspend fun getLastLeagueMatch(id: String): ResultState<EventsMdl> {
            return remoteLeagueDataSource.getLastLeagueMatch(id)
        }

        override suspend fun getNextLeagueMatch(id: String): ResultState<EventsMdl> {
            return remoteLeagueDataSource.getNextLeagueMatch(id)
        }

    }
}