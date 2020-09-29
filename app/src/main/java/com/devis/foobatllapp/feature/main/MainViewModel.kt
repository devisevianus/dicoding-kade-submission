package com.devis.foobatllapp.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devis.foobatllapp.BuildConfig
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.ResponseLeagueMdl
import com.devis.foobatllapp.core.model.ResponseListLeagueMdl
import com.devis.foobatllapp.data.repository.MainRepository
import com.devis.foobatllapp.data.source.remote.ApiClient
import com.devis.foobatllapp.data.source.remote.MainService
import com.devis.foobatllapp.data.source.remote.RemoteMainDataSource
import com.devis.foobatllapp.data.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Devis on 29/09/20
 */

class MainViewModel : ViewModel() {

    private val mLeagueLiveData: MutableLiveData<BaseViewState<List<ResponseLeagueMdl>>> = MutableLiveData()
    private val mRepository: MainRepository.MainRepositoryImpl
    private val mRemoteDataSource: RemoteMainDataSource
    private val mApiService: MainService = ApiClient
        .retrofitClient(BuildConfig.BASE_URL).create(MainService::class.java)

    val leagueLiveData: LiveData<BaseViewState<List<ResponseLeagueMdl>>> = mLeagueLiveData

    init {
        mRemoteDataSource = RemoteMainDataSource(mApiService)
        mRepository = MainRepository.MainRepositoryImpl(mRemoteDataSource)
    }

    fun getLeagueDetail(id: String) {
        mLeagueLiveData.value = BaseViewState.Loading
        viewModelScope.launch(Dispatchers.Main) {
            val request = withContext(Dispatchers.IO) {
                mRepository.getLeagueDetail(id)
            }

            when(request) {
                is ResultState.Success -> {
                    mLeagueLiveData.value = BaseViewState.Success(request.data?.leagues)
                }
                is ResultState.Error -> {
                    mLeagueLiveData.value = BaseViewState.Error(request.errorMessage)
                }
            }
        }
    }

}