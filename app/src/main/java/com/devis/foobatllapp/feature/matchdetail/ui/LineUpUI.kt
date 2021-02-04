package com.devis.foobatllapp.feature.matchdetail.ui

import com.devis.foobatllapp.R
import com.devis.foobatllapp.feature.matchdetail.presentation.LineUpFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

class LineUpUI : AnkoComponent<LineUpFragment> {

    override fun createView(ui: AnkoContext<LineUpFragment>) = with(ui) {
        recyclerView {
            lparams(matchParent, matchParent)
            id = R.id.rv_line_up
        }
    }

}