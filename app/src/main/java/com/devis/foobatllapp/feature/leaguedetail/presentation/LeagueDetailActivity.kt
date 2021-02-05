package com.devis.foobatllapp.feature.leaguedetail.presentation

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseActivity
import com.devis.foobatllapp.core.model.ResponseLeagueMdl
import com.devis.foobatllapp.feature.leaguedetail.ui.LeagueDetailUI
import com.devis.foobatllapp.feature.search.presentation.SearchMatchActivity
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_search) {
            SearchMatchActivity.startThisActivity(this)
        }
        return super.onOptionsItemSelected(item)
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