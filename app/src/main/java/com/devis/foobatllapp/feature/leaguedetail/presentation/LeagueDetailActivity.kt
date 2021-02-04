package com.devis.foobatllapp.feature.leaguedetail.presentation

import android.content.Context
import android.os.Bundle
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseActivity
import com.devis.foobatllapp.core.model.ResponseLeagueMdl
import com.devis.foobatllapp.feature.leaguedetail.ui.LeagueDetailUI
import com.google.gson.Gson
import org.jetbrains.anko.*

/**
 * Created by Devis on 20/09/20
 */

class LeagueDetailActivity : BaseActivity() {

    companion object {
        private const val EXTRA_LEAGUE = "league"

        fun startThisActivity(context: Context, leagueMdl: ResponseLeagueMdl) {
            context.startActivity<LeagueDetailActivity>(
                EXTRA_LEAGUE to Gson().toJson(leagueMdl)
            )
        }
    }

    private var mLeagueMdl: ResponseLeagueMdl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        LeagueDetailUI(mLeagueMdl).setContentView(this)
        initToolbar()
        setStatusBarColor()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initToolbar() {
        setSupportActionBar(findOptional(R.id.toolbar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun getExtraData() {
        intent.extras?.let {
            mLeagueMdl = Gson().fromJson(it.get(EXTRA_LEAGUE).toString(), ResponseLeagueMdl::class.java)
        }
    }

}