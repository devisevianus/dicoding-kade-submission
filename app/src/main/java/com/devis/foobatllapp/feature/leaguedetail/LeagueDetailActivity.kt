package com.devis.foobatllapp.feature.leaguedetail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.LeagueMdl
import com.devis.foobatllapp.core.util.setImage
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * Created by Devis on 20/09/20
 */

class LeagueDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_LEAGUE = "league"

        fun startThisActivity(context: Context, leagueMdl: LeagueMdl) {
            context.startActivity<LeagueDetailActivity>(
                EXTRA_LEAGUE to Gson().toJson(leagueMdl)
            )
        }
    }

    private var mLeagueMdl: LeagueMdl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        LeagueDetailUI(mLeagueMdl).setContentView(this)
        initToolbar()
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
            mLeagueMdl = Gson().fromJson(it.get(EXTRA_LEAGUE).toString(), LeagueMdl::class.java)
        }
    }

}