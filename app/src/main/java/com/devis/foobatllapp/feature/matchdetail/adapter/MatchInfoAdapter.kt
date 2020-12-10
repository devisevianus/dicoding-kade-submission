package com.devis.foobatllapp.feature.matchdetail.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.CustomEventMdl
import com.devis.foobatllapp.feature.matchdetail.ui.MatchInfoItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class MatchInfoAdapter(
    private val listEvent: List<CustomEventMdl>
) : RecyclerView.Adapter<MatchInfoAdapter.ViewHolder>() {

    private var homeScore = 0
    private var awayScore = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MatchInfoItemUI().createView(
                AnkoContext.Companion.create(parent.context, this)
            )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listEvent.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(listEvent[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvEventTime = itemView.find<TextView>(R.id.tv_event_time)
        private val tvEventValueHome = itemView.find<TextView>(R.id.tv_event_value_home)
        private val tvEventValueAway = itemView.find<TextView>(R.id.tv_event_value_away)
        private val tvHomeScore = itemView.find<TextView>(R.id.tv_score_home)
        private val tvAwayScore = itemView.find<TextView>(R.id.tv_score_away)
        private val tvDivider = itemView.find<TextView>(R.id.tv_divider)

        fun bind(item: CustomEventMdl) {
            tvEventTime.text = item.eventValue.split(":")[0]
            if (item.eventSide.equals("home", ignoreCase = true)) {
                tvEventValueHome.text = item.eventValue.split(":")[1]
            } else if (item.eventSide.equals("away", ignoreCase = true)) {
                tvEventValueAway.text = item.eventValue.split(":")[1]
            }

            if (item.eventSide.equals("home", ignoreCase = true)
                && item.eventType.equals("goal", ignoreCase = true)) {
                homeScore += 1
                tvHomeScore.text = homeScore.toString()
                tvAwayScore.text = awayScore.toString()
                tvDivider.visibility = View.VISIBLE
            } else if (item.eventSide.equals("away", ignoreCase = true)
                && item.eventType.equals("goal", ignoreCase = true)) {
                awayScore += 1
                tvHomeScore.text = homeScore.toString()
                tvAwayScore.text = awayScore.toString()
                tvDivider.visibility = View.VISIBLE
            }
        }
    }

}