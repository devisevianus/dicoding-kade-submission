package com.devis.foobatllapp.feature.match.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseFragment
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.feature.match.ui.MatchUI
import com.devis.foobatllapp.feature.match.adapter.MatchAdapter
import com.devis.foobatllapp.feature.matchdetail.presentation.MatchDetailActivity
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

    private lateinit var mAdapter: MatchAdapter
    private lateinit var mRvEventList: RecyclerView

    private var mLeagueId: String = ""

    private val mViewModel: MatchViewModel by viewModels()
    private val mListEvent: ArrayList<EventMdl> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MatchUI()
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
        mLeagueId = arguments?.getString(EXTRA_LEAGUE_ID).orEmpty()
    }

    private fun initObserve() {
        mViewModel.run {
            listLeagueLastEvent.observe(viewLifecycleOwner, Observer {
                when(it) {
                    is BaseViewState.Loading -> {
                        Log.d("lastMatch", "LOADING")
                    }
                    is BaseViewState.Success -> {
                        Log.d("lastMatch", "SUCCESS")
                        if (!it.data?.events.isNullOrEmpty()) {
                            mListEvent.clear()
                            mListEvent.addAll(it.data?.events!!.take(15))
                            mAdapter.notifyDataSetChanged()
                        }
                    }
                    is BaseViewState.Error -> {
                        Log.e("lastMatch", it.errorMessage.orEmpty())
                        Log.d("lastMatch", "ERROR")
                    }
                }
            })
        }
    }

    private fun initView() {
        mRvEventList = findOptional(R.id.rv_match_list)!!
    }

    private fun initRecyclerView() {
        mListEvent.clear()
        mAdapter = MatchAdapter(mListEvent) {
            MatchDetailActivity.startThisActivity(mContext, it)
        }
        mRvEventList.apply {
            layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
    }

}