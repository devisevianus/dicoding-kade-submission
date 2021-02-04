package com.devis.foobatllapp.data.source.remote

import com.devis.foobatllapp.core.model.ResponseListLeagueMdl
import com.devis.foobatllapp.data.source.MainDataSource
import com.devis.foobatllapp.data.util.ResultState
import com.devis.foobatllapp.data.util.fetchState
import com.devis.foobatllapp.data.util.handleError

/**
 * Created by Devis on 29/09/20
 */

class RemoteMainDataSource(
    private val apiService: MainService
) : MainDataSource {

    override suspend fun getLeagueDetail(id: String): ResultState<ResponseListLeagueMdl> {
        return fetchState {
            val response = apiService.getLeagueDetail(id)
            if (response.isSuccessful) {
                ResultState.Success(response.body())
            } else {
                ResultState.Error(response.handleError().message)
            }
        }
    }

}