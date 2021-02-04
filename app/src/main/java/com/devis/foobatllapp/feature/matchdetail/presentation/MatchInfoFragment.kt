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
import com.devis.foobatllapp.core.model.CustomEventMdl
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.feature.matchdetail.adapter.MatchInfoAdapter
import com.devis.foobatllapp.feature.matchdetail.ui.MatchInfoUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.findOptional

class MatchInfoFragment : BaseFragment() {

    companion object {
        fun newInstance(eventMdl: EventMdl?): Fragment {
            val fragment = MatchInfoFragment()
            return fragment.apply {
                arguments = bundleOf().apply {
                    putSerializable("data", eventMdl)
                }
            }
        }
    }

    private lateinit var mAdapter: MatchInfoAdapter
    private lateinit var mRecyclerView: RecyclerView

    private var mEventMdl: EventMdl? = null
    private var mListEvent: ArrayList<CustomEventMdl> = arrayListOf()

    private val mViewModel: MatchDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MatchInfoUI().createView(
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
        //mViewModel.getEventDetailById(mEventMdl?.id_event.toString())
    }

    private fun getExtraData() {
        mEventMdl = arguments?.getSerializable("data") as EventMdl
    }

    private fun subscribeToLiveData() {
        mViewModel.run {
            listEvent.observe(viewLifecycleOwner, Observer {
                when(it) {
                    is BaseViewState.Loading -> {}
                    is BaseViewState.Success -> {
                        mListEvent.clear()
                        val homeGoal = it.data?.events!![0].home_goal_details
                        val homeRed = it.data.events[0].home_red_cards
                        val homeYellow = it.data.events[0].home_yellow_cards
                        val awayGoal = it.data.events[0].away_goal_details
                        val awayRed = it.data.events[0].away_red_cards
                        val awayYellow = it.data.events[0].away_yellow_cards
                        if (!homeGoal.isNullOrEmpty()) {
                            homeGoal.split(";").forEach { s ->
                                if (s.isNotBlank()) {
                                    val customEventMdl = CustomEventMdl(
                                        eventSide = "home",
                                        eventType = "goal",
                                        eventValue = s
                                    )
                                    mListEvent.add(customEventMdl)
                                }
                            }
                        }
                        if (!homeYellow.isNullOrEmpty()) {
                            homeYellow.split(";").forEach { s ->
                                if (s.isNotBlank() && mListEvent.any { event ->
                                        event.eventSide == "home"
                                        && event.eventType == "yellow"
                                        && event.eventValue == s
                                    }.not()) {
                                    val customEventMdl = CustomEventMdl(
                                        eventSide = "home",
                                        eventType = "yellow",
                                        eventValue = s
                                    )
                                    mListEvent.add(customEventMdl)
                                }
                            }
                        }
                        if (!homeRed.isNullOrEmpty()) {
                            homeRed.split(";").forEach { s ->
                                if (s.isNotBlank()) {
                                    val customEventMdl = CustomEventMdl(
                                        eventSide = "home",
                                        eventType = "red",
                                        eventValue = s
                                    )
                                    mListEvent.add(customEventMdl)
                                }
                            }
                        }
                        if (!awayGoal.isNullOrEmpty()) {
                            awayGoal.split(";").forEach { s ->
                                if (s.isNotBlank()) {
                                    val customEventMdl = CustomEventMdl(
                                        eventSide = "away",
                                        eventType = "goal",
                                        eventValue = s
                                    )
                                    mListEvent.add(customEventMdl)
                                }
                            }
                        }
                        if (!awayYellow.isNullOrEmpty()) {
                            var yellowValue = ""
                            awayYellow.split(";").forEach { s ->
                                if (yellowValue.equals(s.substringAfter(":"), ignoreCase = true).not()) {
                                    yellowValue = s.substringAfter(":")
                                    if (s.isNotBlank()) {
                                        val customEventMdl = CustomEventMdl(
                                            eventSide = "away",
                                            eventType = "yellow",
                                            eventValue = s
                                        )
                                        mListEvent.add(customEventMdl)
                                    }
                                }
                            }
                        }
                        if (!awayRed.isNullOrEmpty()) {
                            awayRed.split(";").forEach { s ->
                                if (s.isNotBlank()) {
                                    val customEventMdl = CustomEventMdl(
                                        eventSide = "away",
                                        eventType = "red",
                                        eventValue = s
                                    )
                                    mListEvent.add(customEventMdl)
                                }
                            }
                        }
                        mListEvent.apply {
                            sortBy { customEventMdl ->
                                customEventMdl.eventValue.split("'")[0].toInt()
                            }
                        }
                        mAdapter = MatchInfoAdapter(mListEvent)
                        mRecyclerView.adapter = mAdapter
                    }
                    is BaseViewState.Error -> {}
                }
            })
        }
    }

    private fun initRecyclerView() {
        mRecyclerView = findOptional(R.id.rv_match_info)!!
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        }
    }

}