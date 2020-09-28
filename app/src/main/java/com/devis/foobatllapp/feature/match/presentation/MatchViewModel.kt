package com.devis.foobatllapp.feature.match.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devis.foobatllapp.BuildConfig
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.ResultsMdl
import com.devis.foobatllapp.data.repository.LeagueRepository
import com.devis.foobatllapp.data.source.remote.ApiClient
import com.devis.foobatllapp.data.source.remote.ApiService
import com.devis.foobatllapp.data.source.remote.RemoteLeagueDataSource
import com.devis.foobatllapp.data.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Devis on 27/09/20
 */

class MatchViewModel : ViewModel() {

    private val mListEvent: MutableLiveData<BaseViewState<ResultsMdl>> = MutableLiveData()
    private val mListLeagueEvent: MutableLiveData<BaseViewState<EventsMdl>> = MutableLiveData()
    private val mRepository: LeagueRepository.LeagueRepositoryImpl
    private val mRemoteDataSource: RemoteLeagueDataSource
    private val mApiService: ApiService = ApiClient
        .retrofitClient(BuildConfig.BASE_URL).create(ApiService::class.java)
    private var jobCallApi: Job? = null

    val listEvent: LiveData<BaseViewState<ResultsMdl>> = mListEvent
    val listLeagueEvent: LiveData<BaseViewState<EventsMdl>> = mListLeagueEvent

    init {
        mRemoteDataSource = RemoteLeagueDataSource(mApiService)
        mRepository = LeagueRepository.LeagueRepositoryImpl(mRemoteDataSource)
    }

    override fun onCleared() {
        super.onCleared()
        jobCallApi?.cancel()
    }

    fun getLastFiveMatch(id: String) {
        mListEvent.value = BaseViewState.Loading
        jobCallApi = viewModelScope.launch(Dispatchers.Main) {
            val request = withContext(Dispatchers.IO) {
                mRepository.getLastFiveMatch("133602")
            }

            when(request) {
                is ResultState.Success -> {
                    mListEvent.value = BaseViewState.Success(request.data)
                }
                is ResultState.Error -> {
                    mListEvent.value = BaseViewState.Error(request.errorMessage)
                }
            }
        }
    }

    fun getLastLeagueMatch(id: String) {
        mListEvent.value = BaseViewState.Loading
        jobCallApi = viewModelScope.launch(Dispatchers.Main) {
            val request = withContext(Dispatchers.IO) {
                mRepository.getLastLeagueMatch(id)
            }

            when(request) {
                is ResultState.Success -> {
                    mListLeagueEvent.value = BaseViewState.Success(request.data)
                }
                is ResultState.Error -> {
                    mListLeagueEvent.value = BaseViewState.Error(request.errorMessage)
                }
            }
        }
    }

}