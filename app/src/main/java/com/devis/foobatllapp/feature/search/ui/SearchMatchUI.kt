package com.devis.foobatllapp.feature.search.ui

import android.graphics.Color
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.devis.foobatllapp.R
import com.devis.foobatllapp.feature.search.presentation.SearchMatchActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SearchMatchUI : AnkoComponent<SearchMatchActivity> {

    override fun createView(ui: AnkoContext<SearchMatchActivity>) = with(ui) {
        constraintLayout {
            lparams(matchParent, matchParent)
            toolbar {
                id = R.id.toolbar
                context?.setTheme(R.style.ToolbarTheme)
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                editText {
                    id = R.id.et_search
                    hint = "Search by club name"
                    hintTextColor = Color.WHITE
                    textColor = Color.WHITE
                    maxLines = 1
                    imeOptions = EditorInfo.IME_ACTION_SEARCH
                }.lparams(matchParent, wrapContent)
            }.lparams(matchParent, wrapContent) {
                topToTop = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
            }
            recyclerView {
                id = R.id.rv_match_list
                lparams {
                    topPadding = dip(16)
                }
            }.lparams(matchParent, dip(0)) {
                topToBottom = R.id.toolbar
                bottomToBottom = ConstraintSet.PARENT_ID
            }
        }
    }
}