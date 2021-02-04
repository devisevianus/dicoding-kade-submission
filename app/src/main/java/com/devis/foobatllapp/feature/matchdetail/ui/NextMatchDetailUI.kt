package com.devis.foobatllapp.feature.matchdetail.ui

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.feature.matchdetail.presentation.NextMatchDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.guideline

class NextMatchDetailUI(
    private val eventMdl: EventMdl?
) : AnkoComponent<NextMatchDetailActivity> {

    override fun createView(ui: AnkoContext<NextMatchDetailActivity>) = with(ui) {
        verticalLayout {
            toolbar {
                id = R.id.toolbar
                title = "Match Detail"
                context?.setTheme(R.style.ToolbarTheme)
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                setTitleTextColor(Color.WHITE)
            }

            verticalLayout {
                backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                cardView {
                    radius = 8F
                    constraintLayout {
                        textView {
                            id = R.id.tv_team_home
                            text = eventMdl?.team_home
                        }.lparams {
                            marginStart = dip(16)
                            startToStart = ConstraintSet.PARENT_ID
                            topToTop = ConstraintSet.PARENT_ID
                            bottomToBottom = ConstraintSet.PARENT_ID
                        }

                        textView {
                            id = R.id.tv_team_away
                            text = eventMdl?.team_away
                        }.lparams {
                            marginEnd = dip(16)
                            endToEnd = ConstraintSet.PARENT_ID
                            topToTop = ConstraintSet.PARENT_ID
                            bottomToBottom = ConstraintSet.PARENT_ID
                        }

                        textView {
                            id = R.id.tv_score_home
                            val score = eventMdl?.home_score ?: "-"
                            text = score.toString()
                        }.lparams(wrapContent, wrapContent) {
                            topToTop = R.id.tv_team_home
                            bottomToBottom = R.id.tv_team_home
                            endToStart = R.id.guideline
                            rightMargin = dip(24)
                        }

                        textView {
                            id = R.id.tv_score_away
                            val score = eventMdl?.away_score ?: "-"
                            text = score.toString()
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
                }.lparams(matchParent, dip(72)) {
                    setMargins(dip(16), dip(16), dip(16), dip(16))
                }
            }.lparams(matchParent, wrapContent)

            constraintLayout {
                textView {
                    id = R.id.tv_title_match_info
                    text = resources.getString(R.string.label_match_info)
                    textSize = 21F
                }.lparams {
                    topMargin = dip(16)
                    topToTop = ConstraintSet.PARENT_ID
                }
                imageView {
                    id = R.id.iv_kickoff
                    image = ContextCompat.getDrawable(context, R.drawable.ic_clock)
                }.lparams {
                    topMargin = dip(16)
                    startToStart = ConstraintSet.PARENT_ID
                    topToBottom = R.id.tv_title_match_info
                }
                textView {
                    id = R.id.tv_kickoff
                    text = resources.getString(R.string.label_kickoff)
                    textSize = 16F
                }.lparams {
                    leftMargin = dip(12)
                    topToTop = R.id.iv_kickoff
                    bottomToBottom = R.id.iv_kickoff
                    startToEnd = R.id.iv_kickoff
                }
                textView {
                    id = R.id.tv_kickoff_value
                    text = String.format(
                        resources.getString(R.string.placeholder_kickoff),
                        eventMdl?.date_event,
                        eventMdl?.str_time
                    )
                    textSize = 16F
                }.lparams {
                    topMargin = dip(6)
                    startToStart = R.id.tv_kickoff
                    topToBottom = R.id.tv_kickoff
                }
                imageView {
                    id = R.id.iv_competition
                    image = ContextCompat.getDrawable(context, R.drawable.ic_group)
                }.lparams {
                    topMargin = dip(12)
                    startToStart = R.id.iv_kickoff
                    endToEnd = R.id.iv_kickoff
                    topToBottom = R.id.tv_kickoff_value
                }
                textView {
                    id = R.id.tv_competition
                    text = resources.getString(R.string.label_competition)
                    textSize = 16F
                }.lparams {
                    topToTop = R.id.iv_competition
                    bottomToBottom = R.id.iv_competition
                    startToStart = R.id.tv_kickoff
                }
                textView {
                    id = R.id.tv_competition_value
                    text = eventMdl?.str_league
                    textSize = 16F
                }.lparams {
                    topMargin = dip(6)
                    topToBottom = R.id.tv_competition
                    startToStart = R.id.tv_competition
                }
                imageView {
                    id = R.id.iv_stadium
                    image = ContextCompat.getDrawable(context, R.drawable.ic_stadium)
                }.lparams {
                    topMargin = dip(12)
                    startToStart = R.id.iv_kickoff
                    endToEnd = R.id.iv_kickoff
                    topToBottom = R.id.tv_competition_value
                }
                textView {
                    id = R.id.tv_stadium
                    text = resources.getString(R.string.label_stadium)
                    textSize = 16F
                }.lparams {
                    topToTop = R.id.iv_stadium
                    bottomToBottom = R.id.iv_stadium
                    startToStart = R.id.tv_competition
                }
                textView {
                    id = R.id.tv_stadium_value
                    text = eventMdl?.stadium
                    textSize = 16F
                }.lparams {
                    topMargin = dip(6)
                    topToBottom = R.id.tv_stadium
                    startToStart = R.id.tv_stadium
                }
                lparams {
                    horizontalPadding = dip(24)
                }
            }
        }
    }

}