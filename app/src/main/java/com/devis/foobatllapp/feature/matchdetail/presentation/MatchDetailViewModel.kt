package com.devis.foobatllapp.feature.matchdetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devis.foobatllapp.BuildConfig
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.EventsMdl
import com.devis.foobatllapp.core.model.StatisticsMdl
import com.devis.foobatllapp.data.repository.MatchRepository
import com.devis.foobatllapp.data.source.remote.ApiClient
import com.devis.foobatllapp.data.source.remote.MatchService
import com.devis.foobatllapp.data.source.remote.RemoteMatchDataSource
import com.devis.foobatllapp.data.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchDetailViewModel : ViewModel() {

    private val mListEvent: MutableLiveData<BaseViewState<EventsMdl>> = MutableLiveData()
    private val mListStatistic: MutableLiveData<BaseViewState<StatisticsMdl>> = MutableLiveData()
    private val mRepository: MatchRepository.MatchRepositoryImpl
    private val mRemoteDataSource: RemoteMatchDataSource
    private val mApiService: MatchService = ApiClient
        .retrofitClient(BuildConfig.BASE_URL).create(MatchService::class.java)

    private var jobCallApi: Job? = null

    val listEvent: LiveData<BaseViewState<EventsMdl>> = mListEvent
    val listStatistic: LiveData<BaseViewState<StatisticsMdl>> = mListStatistic

    init {
        mRemoteDataSource = RemoteMatchDataSource(mApiService)
        mRepository = MatchRepository.MatchRepositoryImpl(mRemoteDataSource)
    }

    override fun onCleared() {
        super.onCleared()
        jobCallApi?.cancel()
    }

    fun getEventDetailById(id: String) {
        mListEvent.value = BaseViewState.Loading
        jobCallApi = viewModelScope.launch(Dispatchers.Main) {
            val request = withContext(Dispatchers.IO) {
                mRepository.getEventDetailById(id)
            }

            when (request) {
                is ResultState.Success -> {
                    mListEvent.value = BaseViewState.Success(request.data)
                }
                is ResultState.Error -> {
                    mListEvent.value = BaseViewState.Error(request.errorMessage)
                }
                is ResultState.ErrorWithCode -> {
                }
            }
        }
    }

    fun getEventStatisticsById(id: String) {
        mListStatistic.value = BaseViewState.Loading
        jobCallApi = viewModelScope.launch(Dispatchers.Main) {
            val request = withContext(Dispatchers.IO) {
                mRepository.getEventStatisticsById(id)
            }

            when (request) {
                is ResultState.Success -> {
                    mListStatistic.value = BaseViewState.Success(request.data)
                }
                is ResultState.Error -> {
                    mListStatistic.value = BaseViewState.Error(request.errorMessage)
                }
                is ResultState.ErrorWithCode -> {
                }
            }
        }
    }

}