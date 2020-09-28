package com.devis.foobatllapp.feature.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.base.BaseFragment
import org.jetbrains.anko.*

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

    private var mLeagueDetail: String = ""

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
            verticalLayout {
                textView {
                    id = R.id.tv_league_detail
                    text = owner.arguments?.getString(EXTRA_LEAGUE_DETAIL).orEmpty()
                    textSize = 16F
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    setLineSpacing(0F, 1.5F)
                }.lparams(matchParent, wrapContent) {
                    topMargin = dip(24)
                    leftMargin = dip(24)
                    rightMargin = dip(24)
                }
            }
        }

    }

}