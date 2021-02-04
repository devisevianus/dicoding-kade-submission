package com.devis.foobatllapp.feature.matchdetail.ui

import androidx.constraintlayout.widget.ConstraintSet
import com.devis.foobatllapp.R
import com.devis.foobatllapp.feature.matchdetail.adapter.StatisticsAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class StatisticsItemUI : AnkoComponent<StatisticsAdapter> {

    override fun createView(ui: AnkoContext<StatisticsAdapter>) = with(ui) {
        constraintLayout {
            lparams(matchParent, wrapContent) {
                topPadding = dip(4)
                bottomPadding = dip(4)
            }
            textView {
                id = R.id.tv_statistic_home
            }.lparams {
                topToTop = ConstraintSet.PARENT_ID
                bottomToBottom = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToStart = R.id.tv_statistic_name
                horizontalBias = 0F
            }

            textView {
                id = R.id.tv_statistic_name
            }.lparams {
                topToTop = ConstraintSet.PARENT_ID
                bottomToBottom = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
            }

            textView {
                id = R.id.tv_statistic_away
            }.lparams {
                topToTop = ConstraintSet.PARENT_ID
                bottomToBottom = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
                startToEnd = R.id.tv_statistic_name
                horizontalBias = 1F
            }
        }
    }

}