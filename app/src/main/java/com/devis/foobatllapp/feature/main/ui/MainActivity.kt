package com.devis.foobatllapp.feature.main.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.LeagueMdl
import com.devis.foobatllapp.feature.leaguedetail.LeagueDetailActivity
import com.devis.foobatllapp.feature.main.adapter.LeagueAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: LeagueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        MainActivityUI().setContentView(this)
    }

    private fun initAdapter() {
        mAdapter =
            LeagueAdapter(leagueList()) { leagueMdl ->
                LeagueDetailActivity.startThisActivity(this, leagueMdl)
            }
    }

    private fun leagueList(): ArrayList<LeagueMdl> {
        val listLeagueMdl = arrayListOf<LeagueMdl>()
        val leagueName = resources.getStringArray(R.array.leagueName)
        val leagueDetail = resources.getStringArray(R.array.leagueDetail)
        val leagueBackground = resources.obtainTypedArray(R.array.leagueBackground)
        val leagueLogo = resources.obtainTypedArray(R.array.leagueLogo)
        for (i in leagueName.indices) {
            val leagueMdl = LeagueMdl(
                leagueLogo.getResourceId(i, 0),
                leagueName[i],
                leagueDetail[i],
                leagueBackground.getResourceId(i, 0)
            )
            listLeagueMdl.add(leagueMdl)
        }
        leagueLogo.recycle()
        leagueBackground.recycle()
        return listLeagueMdl
    }

    class MainActivityUI : AnkoComponent<MainActivity> {

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                toolbar {
                    title = context.getString(R.string.app_name)
                    setTitleTextColor(Color.WHITE)
                    backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                }
                recyclerView {
                    layoutManager = LinearLayoutManager(
                        context, RecyclerView.VERTICAL, false)
                    adapter = owner.mAdapter
                    padding = dip(16)
                }.lparams(matchParent, wrapContent)
            }
        }

    }

}