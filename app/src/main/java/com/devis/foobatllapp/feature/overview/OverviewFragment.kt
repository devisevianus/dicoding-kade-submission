package com.devis.foobatllapp.feature.overview

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * Created by Devis on 27/09/20
 */

class OverviewFragment : BaseFragment() {

    companion object {
        private const val EXTRA_LEAGUE_DETAIL = "leagueDetail"

        fun newInstance(leagueDetail: String): Fragment {
            val fragment =
                OverviewFragment()
            val bundle = Bundle()
            bundle.apply {
                putString(EXTRA_LEAGUE_DETAIL, leagueDetail)
            }
            return fragment.apply { arguments = bundle }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return OverviewUI()
            .createView(AnkoContext.Companion.create(mContext, this))
    }

    class OverviewUI : AnkoComponent<OverviewFragment> {

        override fun createView(ui: AnkoContext<OverviewFragment>) = with(ui) {
            nestedScrollView {
                verticalLayout {
                    textView {
                        id = R.id.tv_league_detail
                        text = owner.arguments?.getString(EXTRA_LEAGUE_DETAIL).orEmpty()
                        textSize = 14F
                        textAlignment = View.TEXT_ALIGNMENT_INHERIT
                        setLineSpacing(0F, 1.5F)
                    }.lparams(matchParent, wrapContent) {
                        margin = dip(24)
                    }
                }
            }
        }

    }

}