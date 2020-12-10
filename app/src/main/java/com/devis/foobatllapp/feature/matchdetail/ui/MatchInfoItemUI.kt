package com.devis.foobatllapp.feature.matchdetail.ui

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import com.devis.foobatllapp.R
import com.devis.foobatllapp.feature.matchdetail.adapter.MatchInfoAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.guideline

class MatchInfoItemUI : AnkoComponent<MatchInfoAdapter> {

    override fun createView(ui: AnkoContext<MatchInfoAdapter>): View = with(ui) {
        constraintLayout {
            lparams(matchParent, wrapContent) {
                topPadding = dip(8)
                bottomPadding = dip(8)
            }

            textView {
                id = R.id.tv_event_time
            }.lparams(wrapContent, wrapContent)

            textView {
                id = R.id.tv_event_value_home
                textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END
            }.lparams(dip(0), wrapContent) {
                marginEnd = dip(32)
                startToEnd = R.id.tv_event_time
                endToStart = R.id.guideline
            }

            textView {
                id = R.id.tv_divider
                text = "-"
                visibility = View.GONE
            }.lparams {
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
            }

            textView {
                id = R.id.tv_score_home
            }.lparams {
                endToStart = R.id.guideline
                marginEnd = dip(12)
            }

            textView {
                id = R.id.tv_score_away
            }.lparams {
                startToEnd = R.id.guideline
                marginStart = dip(12)
            }

            textView {
                id = R.id.tv_event_value_away
            }.lparams(dip(0), wrapContent) {
                marginStart = dip(32)
                startToEnd = R.id.guideline
                endToEnd = ConstraintSet.PARENT_ID
            }

            guideline {
                id = R.id.guideline
            }.lparams {
                orientation = ConstraintSet.VERTICAL_GUIDELINE
                guidePercent = 0.5F
            }
        }
    }

}