package com.devis.foobatllapp.data.source.remote

import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.StatisticsMdl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchService {

    @GET("api/v1/json/1/lookupevent.php")
    suspend fun getEventDetailById(
        @Query("id") id: String
    ): Response<EventsMdl>

    @GET("api/v1/json/1/lookupeventstats.php")
    suspend fun getEventStatisticsById(
        @Query("id") id: String
    ): Response<StatisticsMdl>

}