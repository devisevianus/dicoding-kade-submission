package com.devis.foobatllapp.data.source

import com.devis.foobatllapp.core.model.ResponseListLeagueMdl
import com.devis.foobatllapp.data.util.ResultState

/**
 * Created by Devis on 29/09/20
 */

interface MainDataSource {

    suspend fun getLeagueDetail(id: String): ResultState<ResponseListLeagueMdl>

}