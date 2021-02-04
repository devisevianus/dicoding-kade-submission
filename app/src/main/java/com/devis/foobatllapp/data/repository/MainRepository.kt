package com.devis.foobatllapp.data.repository

import com.devis.foobatllapp.core.model.ResponseListLeagueMdl
import com.devis.foobatllapp.data.source.remote.RemoteMainDataSource
import com.devis.foobatllapp.data.util.ResultState

/**
 * Created by Devis on 29/09/20
 */

interface MainRepository {

    suspend fun getLeagueDetail(id: String): ResultState<ResponseListLeagueMdl>

    class MainRepositoryImpl(
        private val remoteMainDataSource: RemoteMainDataSource
    ) : MainRepository {

        override suspend fun getLeagueDetail(id: String): ResultState<ResponseListLeagueMdl> {
            return remoteMainDataSource.getLeagueDetail(id)
        }

    }

}