package com.devis.foobatllapp.data.source.remote

import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.ResultsMdl
import com.devis.foobatllapp.core.model.TeamsMdl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Devis on 27/09/20
 */

interface ApiService {

    @GET("api/v1/json/search_all_teams.php")
    suspend fun getListTeamsByLeague(
        @Query("l") league: String
    ): Response<TeamsMdl>

    @GET("api/v1/json/1/eventslast.php")
    suspend fun getLastFiveTeamMatch(
        @Query("id") id: String
    ): Response<ResultsMdl>

    @GET("api/v1/json/1/eventspastleague.php")
    suspend fun getLastLeagueMatch(
        @Query("id") id: String
    ): Response<EventsMdl>

    @GET("api/v1/json/1/eventsnextleague.php")
    suspend fun getNextLeagueMatch(
        @Query("id") id: String
    ): Response<EventsMdl>

}