package com.devis.foobatllapp.feature.main.ui

import com.devis.foobatllapp.R
import com.devis.foobatllapp.feature.main.adapter.LeagueAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout

/**
 * Created by Devis on 26/09/20
 */

class LeagueItemUI : AnkoComponent<LeagueAdapter> {

    override fun createView(ui: AnkoContext<LeagueAdapter>) = with(ui) {
        verticalLayout {
            lparams(matchParent, wrapContent)
            cardView {
                radius = 8F

                constraintLayout {

                    imageView {
                        id = R.id.iv_league_logo
                    }.lparams(dip(75), dip(75))

                    textView {
                        id = R.id.tv_league_name
                    }.lparams(wrapContent, wrapContent) {
                        leftMargin = dip(16)
                        padding = dip(16)
                        startToEnd = R.id.iv_league_logo
                        topToTop = R.id.iv_league_logo
                        bottomToBottom = R.id.iv_league_logo
                    }

                }.lparams(matchParent, wrapContent)

            }.lparams(matchParent, wrapContent) {
                bottomMargin = dip(16)
            }
        }
    }

}