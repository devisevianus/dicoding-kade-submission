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
import com.devis.foobatllapp.feature.match.adapter.NextMatchAdapter
import com.devis.foobatllapp.feature.match.ui.MatchUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.findOptional
import kotlin.collections.ArrayList

class NextMatchFragment : BaseFragment() {

    companion object {
        private const val EXTRA_LEAGUE_ID = "leagueId"

        fun newInstance(leagueId: String): Fragment {
            val fragment = NextMatchFragment()
            val bundle = Bundle().apply { putString(EXTRA_LEAGUE_ID, leagueId) }
            return fragment.apply { arguments = bundle }
        }
    }

    private lateinit var mAdapter: NextMatchAdapter
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
        return MatchUI().createView(AnkoContext.Companion.create(mContext, this))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtraData()
        initObserve()
        initView()
        initRecyclerView()
        mViewModel.getNextLeagueMatch(mLeagueId)
    }

    private fun getExtraData() {
        mLeagueId = arguments?.getString(EXTRA_LEAGUE_ID).orEmpty()
    }

    private fun initObserve() {
        mViewModel.run {
            listLeagueNextEvent.observe(viewLifecycleOwner, Observer {
                when(it) {
                    is BaseViewState.Loading -> {
                        Log.d("nextMatch", "LOADING")
                    }
                    is BaseViewState.Success -> {
                        Log.d("nextMatch", "SUCCESS")
                        if (!it.data?.events.isNullOrEmpty()) {
                            mListEvent.clear()
                            mListEvent.addAll(it.data?.events!!.sortedBy { event ->
                                event.strTimestamp
                            })
                            mAdapter.notifyDataSetChanged()
                        }
                    }
                    is BaseViewState.Error -> {
                        Log.e("nextMatch", it.errorMessage.orEmpty())
                        Log.d("nextMatch", "ERROR")
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
        mAdapter = NextMatchAdapter(mListEvent)
        mRvEventList.apply {
            layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
    }

}