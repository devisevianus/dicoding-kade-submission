package com.devis.foobatllapp.data.repository

import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.StatisticsMdl
import com.devis.foobatllapp.data.source.remote.RemoteMatchDataSource
import com.devis.foobatllapp.data.util.ResultState

interface MatchRepository {

    suspend fun getEventDetailById(id: String): ResultState<EventsMdl>
    suspend fun getEventStatisticsById(id: String): ResultState<StatisticsMdl>

    class MatchRepositoryImpl(
        private val remoteMatchDataSource: RemoteMatchDataSource
    ): MatchRepository {

        override suspend fun getEventDetailById(id: String): ResultState<EventsMdl> {
            return remoteMatchDataSource.getEventDetailById(id)
        }

        override suspend fun getEventStatisticsById(id: String): ResultState<StatisticsMdl> {
            return remoteMatchDataSource.getEventStatisticsById(id)
        }

    }

}