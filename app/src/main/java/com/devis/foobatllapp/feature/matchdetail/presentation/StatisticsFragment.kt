package com.devis.foobatllapp.feature.matchdetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseFragment
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.EventStatisticMdl
import com.devis.foobatllapp.feature.matchdetail.adapter.StatisticsAdapter
import com.devis.foobatllapp.feature.matchdetail.ui.StatisticsUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.findOptional

class StatisticsFragment : BaseFragment() {

    companion object {
        fun newInstance(idEvent: String): Fragment {
            val fragment = StatisticsFragment()
            return fragment.apply {
                arguments = bundleOf().apply {
                    putString("idEvent", idEvent)
                }
            }
        }
    }

    private lateinit var mAdapter: StatisticsAdapter

    private var mIdEvent: String = ""

    private val mViewModel: MatchDetailViewModel by activityViewModels()
    private val mListStatistic: ArrayList<EventStatisticMdl> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return StatisticsUI().createView(
            AnkoContext.Companion.create(
                mContext, this
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getExtraData()
        initRecyclerView()
        subscribeToLiveData()
        mViewModel.getEventStatisticsById(mIdEvent)
    }

    private fun getExtraData() {
        mIdEvent = arguments?.getString("idEvent")!!
    }

    private fun subscribeToLiveData() {
        mViewModel.run {
            listStatistic.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is BaseViewState.Loading -> {}
                    is BaseViewState.Success -> {
                        mListStatistic.addAll(it.data?.eventstats.orEmpty())
                        mAdapter.notifyDataSetChanged()
                    }
                    is BaseViewState.Error -> {}
                }
            })
        }
    }

    private fun initRecyclerView() {
        mAdapter = StatisticsAdapter(mListStatistic)
        val rvStatistic: RecyclerView = findOptional(R.id.rv_statistic)!!
        rvStatistic.apply {
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
    }

}