package com.devis.foobatllapp.feature.main.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseActivity
import com.devis.foobatllapp.core.base.BaseViewState
import com.devis.foobatllapp.core.model.LeagueMdl
import com.devis.foobatllapp.feature.leaguedetail.presentation.LeagueDetailActivity
import com.devis.foobatllapp.feature.main.MainViewModel
import com.devis.foobatllapp.feature.main.adapter.LeagueAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : BaseActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: LeagueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initObserve()
        initAdapter()
        MainActivityUI().setContentView(this)
        setStatusBarColor()
    }

    private fun initObserve() {
        mViewModel.apply {
            leagueLiveData.observe(this@MainActivity, Observer {
                when(it) {
                    is BaseViewState.Loading -> {}
                    is BaseViewState.Success -> {
                        when {
                            it.data.isNullOrEmpty() -> {
                                toast("League not found")
                            }
                            else -> {
                                LeagueDetailActivity.startThisActivity(
                                    this@MainActivity,
                                    it.data[0]
                                )
                            }
                        }
                    }
                    is BaseViewState.Error -> {
                        toast(it.errorMessage.orEmpty())
                    }
                }
            })
        }
    }

    private fun initAdapter() {
        mAdapter =
            LeagueAdapter(leagueList()) { leagueMdl ->
                mViewModel.getLeagueDetail(leagueMdl.leagueId)
            }
    }

    private fun leagueList(): ArrayList<LeagueMdl> {
        val listLeagueMdl = arrayListOf<LeagueMdl>()
        val leagueName = resources.getStringArray(R.array.leagueName)
        val leagueDetail = resources.getStringArray(R.array.leagueDetail)
        val leagueBackground = resources.obtainTypedArray(R.array.leagueBackground)
        val leagueLogo = resources.obtainTypedArray(R.array.leagueLogo)
        val leagueId = resources.getStringArray(R.array.leagueId)
        for (i in leagueName.indices) {
            val leagueMdl = LeagueMdl(
                leagueId[i],
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