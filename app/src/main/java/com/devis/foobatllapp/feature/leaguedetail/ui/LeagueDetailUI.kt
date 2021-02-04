package com.devis.foobatllapp.feature.leaguedetail.ui

import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.ResponseLeagueMdl
import com.devis.foobatllapp.feature.leaguedetail.adapter.LeagueDetailAdapter
import com.devis.foobatllapp.feature.leaguedetail.presentation.LeagueDetailActivity
import com.devis.foobatllapp.feature.overview.OverviewFragment
import com.devis.foobatllapp.feature.match.presentation.LastMatchFragment
import com.devis.foobatllapp.feature.match.presentation.NextMatchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

/**
 * Created by Devis on 26/09/20
 */

class LeagueDetailUI(leagueMdl: ResponseLeagueMdl?) : AnkoComponent<LeagueDetailActivity> {

    private val mLeagueMdl: ResponseLeagueMdl? = leagueMdl

    override fun createView(ui: AnkoContext<LeagueDetailActivity>) = with(ui) {
        coordinatorLayout {
            //fitsSystemWindows = true

            val appBarLayout = appBarLayout {
                //fitsSystemWindows = true

                collapsingToolbarLayout {
                    id = R.id.collapsing_toolbar
                    title = mLeagueMdl?.league_name
                    //fitsSystemWindows = true
                    setContentScrimColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    setExpandedTitleColor(Color.WHITE)
                    setCollapsedTitleTextColor(Color.WHITE)

                    imageView {
                        id = R.id.iv_toolbar_background
                        fitsSystemWindows = true
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams(matchParent, matchParent) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
                    }/*.setImage(imageUrl = mLeagueMdl?.league_banner)*/

                    toolbar {
                        id = R.id.toolbar
                        title = mLeagueMdl?.league_name
                        context?.setTheme(R.style.ToolbarTheme)
                    }.lparams(matchParent, dimenAttr(android.R.attr.actionBarSize)) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
                    }

                }.lparams(matchParent, matchParent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }

            }.lparams(matchParent, 400)

            verticalLayout {
                val adapter =
                    LeagueDetailAdapter(
                        (context as LeagueDetailActivity).supportFragmentManager
                    )
                adapter.apply {
                    add(OverviewFragment.newInstance(mLeagueMdl?.league_description.orEmpty()), "Overview")
                    add(LastMatchFragment.newInstance(mLeagueMdl?.league_id.toString()), "Last Match")
                    add(NextMatchFragment.newInstance(mLeagueMdl?.league_id.toString()), "Next Match")
                }

                val color = when {
                    /*mLeagueMdl?.leagueName.equals("premier league", ignoreCase = true) -> ContextCompat.getColor(context, R.color.colorIndigo)
                    mLeagueMdl?.leagueName.equals("la liga", ignoreCase = true) -> Color.BLACK
                    mLeagueMdl?.leagueName.equals("serie a", ignoreCase = true) -> ContextCompat.getColor(context, R.color.colorDarkCerulean)
                    mLeagueMdl?.leagueName.equals("ligue 1", ignoreCase = true) -> ContextCompat.getColor(context, R.color.colorSapphire)
                    mLeagueMdl?.leagueName.equals("bundesliga", ignoreCase = true) -> ContextCompat.getColor(context, R.color.colorPersianRed)*/
                    else -> ContextCompat.getColor(context, R.color.colorPrimary)
                }

                val tabLayout = tabLayout {
                    lparams(matchParent, wrapContent)
                    id = R.id.tab_layout_league_detail
                    backgroundColor = color
                    setSelectedTabIndicatorColor(Color.WHITE)
                    setTabTextColors(Color.GRAY, Color.WHITE)
                }

                val viewPager = viewPager {
                    id = R.id.view_page_league_detail
                    this.adapter = adapter
                    offscreenPageLimit = adapter.count
                }.lparams(matchParent, matchParent)

                tabLayout.setupWithViewPager(viewPager)

                appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, offset ->
                    Log.d("offsetAppbar", offset.toString())
                    //val alpha = abs(offset) / 100F
                    if (offset <= -145) {
                        tabLayout.animate().withStartAction {
                            //val colorResult = ColorUtils.blendARGB(ContextCompat.getColor(context, R.color.colorTest), Color.WHITE, alpha)
                            tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        }.start()
                    } else {
                        tabLayout.animate().withStartAction {
                            tabLayout.setBackgroundColor(color)
                        }.start()
                    }
                })

            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

}