package com.devis.foobatllapp.feature.matchdetail.ui

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import com.devis.foobatllapp.R
import com.devis.foobatllapp.feature.matchdetail.adapter.LineUpAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.guideline

class LineUpItemUI : AnkoComponent<LineUpAdapter> {

    override fun createView(ui: AnkoContext<LineUpAdapter>): View = with(ui) {
        constraintLayout {
            lparams(matchParent, wrapContent) {
                verticalPadding = dip(8)
                horizontalPadding = dip(16)
            }

            textView {
                id = R.id.tv_line_up_home
            }.lparams {
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = R.id.guideline
                horizontalBias = 0F
            }

            textView {
                id = R.id.tv_line_up_away
                textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END
            }.lparams {
                endToEnd = ConstraintSet.PARENT_ID
                startToStart = R.id.guideline
                horizontalBias = 1F
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