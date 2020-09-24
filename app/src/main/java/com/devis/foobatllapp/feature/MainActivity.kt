package com.devis.foobatllapp.feature

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.LeagueMdl
import com.devis.foobatllapp.core.util.convertToByteArray
import com.devis.foobatllapp.feature.leaguedetail.LeagueDetailActivity
import com.google.gson.Gson
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
        mAdapter = LeagueAdapter(leagueList()) { leagueMdl ->
            startActivity<LeagueDetailActivity>(
                "league" to Gson().toJson(leagueMdl)
            )
        }
    }

    private fun leagueName(): Array<String> {
        return resources.getStringArray(R.array.leagueName)
    }

    private fun leagueLogo(): Array<Drawable?> {
        val ta = resources.obtainTypedArray(R.array.leagueLogo)
        val logo = arrayOfNulls<Drawable>(ta.length())
        for (i in 0 until ta.length()) {
            val id = ta.getResourceId(i, 0)
            if (id != 0) {
                logo[i] = ContextCompat.getDrawable(this, id)
            }
        }
        ta.recycle()
        return logo
    }

    private fun leagueList(): ArrayList<LeagueMdl> {
        val listLeagueMdl = arrayListOf<LeagueMdl>()
        for (i in leagueName().indices) {
            val leagueMdl = LeagueMdl(leagueName()[i], leagueLogo()[i]!!.convertToByteArray())
            listLeagueMdl.add(leagueMdl)
        }
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