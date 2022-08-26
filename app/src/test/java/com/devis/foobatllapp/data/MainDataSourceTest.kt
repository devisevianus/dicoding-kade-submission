package com.devis.foobatllapp.data

import com.devis.foobatllapp.core.model.ResponseLeagueMdl
import com.devis.foobatllapp.core.model.ResponseListLeagueMdl
import com.devis.foobatllapp.data.source.MainDataSource
import com.devis.foobatllapp.data.source.remote.MainService
import com.devis.foobatllapp.data.source.remote.RemoteMainDataSource
import com.devis.foobatllapp.data.util.ResultState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class MainDataSourceTest {

    private lateinit var mainDataSource: MainDataSource
    private lateinit var result: ResultState<ResponseListLeagueMdl>

    private var mApiService = mock(MainService::class.java)

    private val mLeagueMdl = ResponseLeagueMdl(
        league_id = "",
        league_name = "",
        league_description = "",
        league_banner = "",
        league_badge = ""
    )
    private val mResponse = ResponseListLeagueMdl(arrayListOf(mLeagueMdl))

    @Before
    fun setup() {
        mainDataSource = RemoteMainDataSource(mApiService)
    }

    @Test
    fun positiveCase() {
        val response = ResultState.Success(mResponse)
        runBlocking {
            `when`(mApiService.getLeagueDetail("4328")).thenReturn(Response.success(mResponse))
            val repo = mainDataSource.getLeagueDetail("4328")
            assertEquals(response.data, repo)
        }
    }

}