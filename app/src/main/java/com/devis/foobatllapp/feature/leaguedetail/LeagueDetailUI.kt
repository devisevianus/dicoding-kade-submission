package com.devis.foobatllapp.feature.leaguedetail

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.LeagueMdl
import com.devis.foobatllapp.core.util.setImage
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * Created by Devis on 26/09/20
 */

class LeagueDetailUI(leagueMdl: LeagueMdl?) : AnkoComponent<LeagueDetailActivity> {

    private val mLeagueMdl: LeagueMdl? = leagueMdl

    override fun createView(ui: AnkoContext<LeagueDetailActivity>) = with(ui) {
        coordinatorLayout {
            fitsSystemWindows = true

            appBarLayout {
                fitsSystemWindows = true

                collapsingToolbarLayout {
                    id = R.id.collapsing_toolbar
                    title = mLeagueMdl?.leagueName
                    fitsSystemWindows = true
                    setContentScrimColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    setExpandedTitleColor(Color.WHITE)
                    setCollapsedTitleTextColor(Color.WHITE)

                    imageView {
                        id = R.id.iv_toolbar_background
                        fitsSystemWindows = true
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams(matchParent, matchParent) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
                    }.setImage(mLeagueMdl?.leagueBackground!!)

                    toolbar {
                        id = R.id.toolbar
                        title = mLeagueMdl.leagueName
                        context?.setTheme(R.style.ToolbarTheme)
                    }.lparams(matchParent, dimenAttr(android.R.attr.actionBarSize)) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
                    }

                }.lparams(matchParent, matchParent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }

            }.lparams(matchParent, 500)

            nestedScrollView {

                textView {
                    id = R.id.tv_league_detail
                    text = mLeagueMdl?.leagueDetail
                    textSize = 16F
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    setLineSpacing(0F, 1.5F)
                }.lparams(matchParent, wrapContent) {
                    topMargin = dip(24)
                    leftMargin = dip(24)
                    rightMargin = dip(24)
                }

            }.lparams(matchParent, wrapContent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
    }

}