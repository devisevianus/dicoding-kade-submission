package com.devis.foobatllapp.feature.match.ui

import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by Devis on 27/09/20
 */

class MatchUI : AnkoComponent<BaseFragment> {

    override fun createView(ui: AnkoContext<BaseFragment>) = with(ui) {
        recyclerView {
            lparams(matchParent, matchParent) {
                topPadding = dip(16)
            }
            id = R.id.rv_match_list
        }
    }

}