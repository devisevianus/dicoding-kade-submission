package com.devis.foobatllapp.feature.matchdetail.ui

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.feature.matchdetail.adapter.MatchDetailAdapter
import com.devis.foobatllapp.feature.matchdetail.presentation.MatchDetailActivity
import com.devis.foobatllapp.feature.matchdetail.presentation.MatchInfoFragment
import com.devis.foobatllapp.feature.matchdetail.presentation.StatisticsFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.guideline
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class MatchDetailUI(
    private val eventMdl: EventMdl?
) : AnkoComponent<MatchDetailActivity> {

    override fun createView(ui: AnkoContext<MatchDetailActivity>) = with(ui) {
        verticalLayout {
            toolbar {
                id = R.id.toolbar
                title = "Match Detail"
                context?.setTheme(R.style.ToolbarTheme)
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                setTitleTextColor(Color.WHITE)
            }

            verticalLayout {
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                cardView {
                    radius = 8F
                    constraintLayout {
                        textView {
                            id = R.id.tv_team_home
                        }.lparams {
                            marginStart = dip(16)
                            startToStart = ConstraintSet.PARENT_ID
                            topToTop = ConstraintSet.PARENT_ID
                            bottomToBottom = ConstraintSet.PARENT_ID
                        }

                        textView {
                            id = R.id.tv_team_away
                        }.lparams {
                            marginEnd = dip(16)
                            endToEnd = ConstraintSet.PARENT_ID
                            topToTop = ConstraintSet.PARENT_ID
                            bottomToBottom = ConstraintSet.PARENT_ID
                        }

                        textView {
                            id = R.id.tv_score_home
                        }.lparams(wrapContent, wrapContent) {
                            topToTop = R.id.tv_team_home
                            bottomToBottom = R.id.tv_team_home
                            endToStart = R.id.guideline
                            rightMargin = dip(24)
                        }

                        textView {
                            id = R.id.tv_score_away
                        }.lparams(wrapContent, wrapContent) {
                            topToTop = R.id.tv_team_home
                            bottomToBottom = R.id.tv_team_home
                            startToEnd = R.id.guideline
                            leftMargin = dip(24)
                        }

                        textView {
                            text = ":"
                        }.lparams(wrapContent, wrapContent) {
                            topToTop = R.id.tv_team_home
                            bottomToBottom = R.id.tv_team_home
                            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                        }

                        guideline {
                            id = R.id.guideline
                        }.lparams {
                            orientation = ConstraintLayout.LayoutParams.VERTICAL
                            guidePercent = 0.5F
                        }
                    }
                }.lparams(matchParent, dip(72)) {
                    setMargins(dip(16), dip(16), dip(16), dip(16))
                }
            }.lparams(matchParent, wrapContent)

            val adapter = MatchDetailAdapter((context as MatchDetailActivity).supportFragmentManager)
            adapter.apply {
                add(MatchInfoFragment.newInstance(eventMdl), "Match Info")
                add(StatisticsFragment.newInstance(eventMdl?.id_event.toString()), "Statistics")
                add(StatisticsFragment.newInstance(eventMdl?.id_event.toString()), "Line-Ups")
            }

            val tabLayout = tabLayout {
                lparams(matchParent, wrapContent)
                id = R.id.tab_layout_league_detail
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                setSelectedTabIndicatorColor(Color.WHITE)
                setTabTextColors(Color.GRAY, Color.WHITE)
            }

            val viewPager = viewPager {
                id = R.id.view_pager_match_detail
                this.adapter = adapter
                offscreenPageLimit = adapter.count
            }.lparams(matchParent, matchParent)

            tabLayout.setupWithViewPager(viewPager)
        }
    }

}