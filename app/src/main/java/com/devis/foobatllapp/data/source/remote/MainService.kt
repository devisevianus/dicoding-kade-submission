package com.devis.foobatllapp.data.source.remote

import com.devis.foobatllapp.core.model.ResponseListLeagueMdl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Devis on 29/09/20
 */

interface MainService {

    @GET("api/v1/json/1/lookupleague.php")
    suspend fun getLeagueDetail(
        @Query("id") id: String
    ): Response<ResponseListLeagueMdl>

}