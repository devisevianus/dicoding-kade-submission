package com.devis.foobatllapp.feature.match.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseFragment
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.feature.match.ui.LastMatchUI
import com.devis.foobatllapp.feature.match.adapter.MatchAdapter
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.findOptional

/**
 * Created by Devis on 27/09/20
 */

class LastMatchFragment : BaseFragment() {

    companion object {
        private const val EXTRA_LEAGUE_ID = "leagueId"

        fun newInstance(leagueId: String): Fragment {
            val fragment =
                LastMatchFragment()
            val bundle = Bundle()
            bundle.apply {
                putString(EXTRA_LEAGUE_ID, leagueId)
            }

            return fragment.apply { arguments = bundle }
        }
    }

    private lateinit var mViewModel: MatchViewModel
    private lateinit var mAdapter: MatchAdapter
    private lateinit var mRvEventList: RecyclerView

    private var mLeagueId: String = ""

    private val mListEvent: ArrayList<EventMdl> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MatchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LastMatchUI()
            .createView(AnkoContext.Companion.create(mContext, this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtraData()
        initObserve()
        initView()
        initRecyclerView()
        mViewModel.getLastLeagueMatch(mLeagueId)
    }

    private fun getExtraData() {
        mLeagueId = arguments?.getString(EXTRA_LEAGUE_ID).toString()
    }

    private fun initObserve() {
        mViewModel.run {
            listLeagueEvent.observe(viewLifecycleOwner, Observer {
                when(it) {
                    is BaseViewState.Loading -> {
                        Log.d("matchViewModel", "LOADING")
                    }
                    is BaseViewState.Success -> {
                        Log.d("matchViewModel", "SUCCESS")
                        if (!it.data?.events.isNullOrEmpty()) {
                            mListEvent.clear()
                            mListEvent.addAll(it.data?.events!!)
                            mAdapter.notifyDataSetChanged()
                        }
                    }
                    is BaseViewState.Error -> {
                        Log.e("matchViewModel", it.errorMessage.orEmpty())
                        Log.d("matchViewModel", "ERROR")
                    }
                }
            })
        }
    }

    private fun initView() {
        mRvEventList = findOptional(R.id.rv_last_match)!!
    }

    private fun initRecyclerView() {
        mListEvent.clear()
        mAdapter =
            MatchAdapter(mListEvent)
        mRvEventList.run {
            layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
    }

}