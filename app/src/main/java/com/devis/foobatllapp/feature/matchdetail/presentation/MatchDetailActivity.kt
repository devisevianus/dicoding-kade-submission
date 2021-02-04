package com.devis.foobatllapp.feature.matchdetail.presentation

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseActivity
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.feature.matchdetail.ui.MatchDetailUI
import org.jetbrains.anko.findOptional
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class MatchDetailActivity : BaseActivity() {

    companion object {
        private const val EXTRA_EVENT_DATA = "eventData"

        fun startThisActivity(context: Context, eventMdl: EventMdl) {
            context.startActivity<MatchDetailActivity>(
                EXTRA_EVENT_DATA to eventMdl
            )
        }
    }

    private lateinit var mTvTeamHome: TextView
    private lateinit var mTvTeamAway: TextView
    private lateinit var mTvHomeScore: TextView
    private lateinit var mTvAwayScore: TextView

    private var mEventMdl: EventMdl? = null

    private val mViewModel: MatchDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        MatchDetailUI(mEventMdl).setContentView(this)
        initToolbar()
        initView()
        setStatusBarColor()
        subscribeToLiveData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun getExtraData() {
        mEventMdl = intent.getSerializableExtra(EXTRA_EVENT_DATA) as EventMdl
    }

    private fun initView() {
        mTvTeamHome = findOptional(R.id.tv_team_home)!!
        mTvTeamAway = findOptional(R.id.tv_team_away)!!
        mTvHomeScore = findOptional(R.id.tv_score_home)!!
        mTvAwayScore = findOptional(R.id.tv_score_away)!!

        mTvTeamHome.text = mEventMdl?.team_home
        mTvTeamAway.text = mEventMdl?.team_away
        mTvHomeScore.text = mEventMdl?.home_score.toString()
        mTvAwayScore.text = mEventMdl?.away_score.toString()
    }

    private fun initToolbar() {
        setSupportActionBar(findOptional(R.id.toolbar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun subscribeToLiveData() {
        mViewModel.run {
            getEventDetailById(mEventMdl?.id_event.toString())
            listEvent.observe(this@MatchDetailActivity, Observer {
                when(it) {
                    is BaseViewState.Loading -> {}
                    is BaseViewState.Success -> {}
                    is BaseViewState.Error -> {}
                }
            })
        }
    }

}