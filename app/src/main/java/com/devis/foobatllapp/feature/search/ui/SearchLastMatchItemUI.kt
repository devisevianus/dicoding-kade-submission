package com.devis.foobatllapp.feature.search.ui

import android.text.TextUtils
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.devis.foobatllapp.R
import com.devis.foobatllapp.feature.search.adapter.SearchMatchAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.guideline

class SearchLastMatchItemUI : AnkoComponent<SearchMatchAdapter> {

    override fun createView(ui: AnkoContext<SearchMatchAdapter>) = with(ui) {
        cardView {
            lparams(matchParent, wrapContent) {
                bottomMargin = dip(12)
                leftMargin = dip(24)
                rightMargin = dip(24)
            }

            constraintLayout {
                lparams(matchParent, wrapContent)
                textView {
                    id = R.id.tv_match_date
                }.lparams(wrapContent, wrapContent) {
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    setPadding(dip(24), dip(16), dip(24), dip(16))
                }

                textView {
                    id = R.id.tv_team_home
                    singleLine = true
                    ellipsize = TextUtils.TruncateAt.END
                }.lparams(wrapContent, wrapContent) {
                    topToBottom = R.id.tv_match_date
                    startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                    endToStart = R.id.tv_score_home
                    horizontalBias = 0F
                    constrainedWidth = true
                    topMargin = dip(24)
                    rightMargin = dip(12)
                }

                textView {
                    id = R.id.tv_team_away
                    singleLine = true
                    ellipsize = TextUtils.TruncateAt.END
                    gravity = Gravity.END
                }.lparams(wrapContent, wrapContent) {
                    topToTop = R.id.tv_team_home
                    bottomToBottom = R.id.tv_team_home
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    startToEnd = R.id.tv_score_away
                    horizontalBias = 1F
                    constrainedWidth = true
                    leftMargin = dip(12)
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
        }
    }

}