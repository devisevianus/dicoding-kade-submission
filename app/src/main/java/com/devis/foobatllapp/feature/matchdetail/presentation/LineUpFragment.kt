package com.devis.foobatllapp.feature.matchdetail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseFragment
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.LineUpMdl
import com.devis.foobatllapp.feature.matchdetail.adapter.LineUpAdapter
import com.devis.foobatllapp.feature.matchdetail.ui.LineUpUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.findOptional

class LineUpFragment : BaseFragment() {

    companion object {
        fun newInstance(): Fragment = LineUpFragment()
    }

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: LineUpAdapter

    private val mViewModel: MatchDetailViewModel by activityViewModels()
    private val mLineUpHome: ArrayList<LineUpMdl> = arrayListOf()
    private val mLineUpAway: ArrayList<LineUpMdl> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LineUpUI().createView(
            AnkoContext.Companion.create(mContext, this)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        mViewModel.run {
            listEvent.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is BaseViewState.Loading -> {}
                    is BaseViewState.Success -> {
                        val homeGoalKeeper = it.data?.events!![0].home_goal_keeper
                        val homeDefense = it.data.events[0].home_defense
                        val homeMidfield = it.data.events[0].home_midfield
                        val homeForward = it.data.events[0].home_forward
                        val awayGoalKeeper = it.data.events[0].away_goal_keeper
                        val awayDefense = it.data.events[0].away_defense
                        val awayMidfield = it.data.events[0].away_midfield
                        val awayForward = it.data.events[0].away_forward

                        val lineUpHome = LineUpMdl(
                            position = "GK",
                            name = homeGoalKeeper
                        )
                        mLineUpHome.add(lineUpHome)

                        homeDefense.split(";").forEach { s ->
                            if (s.isNotBlank()) {
                                val lineUpHome1 = LineUpMdl(
                                    position = "DF",
                                    name = s
                                )
                                mLineUpHome.add(lineUpHome1)
                            }
                        }

                        homeMidfield.split(";").forEach { s ->
                            if (s.isNotBlank()) {
                                val lineUpHome1 = LineUpMdl(
                                    position = "MF",
                                    name = s
                                )
                                mLineUpHome.add(lineUpHome1)
                            }
                        }

                        homeForward.split(";").forEach { s ->
                            if (s.isNotBlank()) {
                                val lineUpHome1 = LineUpMdl(
                                    position = "FW",
                                    name = s
                                )
                                mLineUpHome.add(lineUpHome1)
                            }
                        }

                        val lineUpAway = LineUpMdl(
                            position = "GK",
                            name = awayGoalKeeper
                        )
                        mLineUpAway.add(lineUpAway)

                        awayDefense.split(";").forEach { s ->
                            if (s.isNotBlank()) {
                                val lineUpAway1 = LineUpMdl(
                                    position = "DF",
                                    name = s
                                )
                                mLineUpAway.add(lineUpAway1)
                            }
                        }

                        awayMidfield.split(";").forEach { s ->
                            if (s.isNotBlank()) {
                                val lineUpAway1 = LineUpMdl(
                                    position = "MF",
                                    name = s
                                )
                                mLineUpAway.add(lineUpAway1)
                            }
                        }

                        awayForward.split(";").forEach { s ->
                            if (s.isNotBlank()) {
                                val lineUpAway1 = LineUpMdl(
                                    position = "FW",
                                    name = s
                                )
                                mLineUpAway.add(lineUpAway1)
                            }
                        }

                        mAdapter = LineUpAdapter(mLineUpHome, mLineUpAway)
                        mRecyclerView.adapter = mAdapter
                    }
                    is BaseViewState.Error -> {}
                }
            })
        }
    }

    private fun initRecyclerView() {
        mRecyclerView = findOptional(R.id.rv_line_up)!!
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        }
    }

}