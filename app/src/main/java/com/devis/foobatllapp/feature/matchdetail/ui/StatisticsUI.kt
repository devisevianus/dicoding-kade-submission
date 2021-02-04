package com.devis.foobatllapp.feature.matchdetail.ui

import com.devis.foobatllapp.R
import com.devis.foobatllapp.feature.matchdetail.presentation.StatisticsFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class StatisticsUI : AnkoComponent<StatisticsFragment> {

    override fun createView(ui: AnkoContext<StatisticsFragment>) = with(ui) {
        recyclerView {
            id = R.id.rv_statistic
            lparams(matchParent, matchParent) {
                padding = dip(16)
            }
        }
    }

}