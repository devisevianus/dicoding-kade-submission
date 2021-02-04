package com.devis.foobatllapp.feature.match.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.core.util.convertDate
import com.devis.foobatllapp.feature.match.ui.MatchItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by Devis on 27/09/20
 */

class MatchAdapter(
    private val listEvent: ArrayList<EventMdl>,
    private val listener: (EventMdl) -> Unit
) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MatchItemUI()
            .createView(AnkoContext.Companion.create(parent.context, this))
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listEvent[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvEventDate = itemView.find<TextView>(R.id.tv_match_date)
        private val tvTeamHome = itemView.find<TextView>(R.id.tv_team_home)
        private val tvTeamAway = itemView.find<TextView>(R.id.tv_team_away)
        private val tvScoreHome = itemView.find<TextView>(R.id.tv_score_home)
        private val tvScoreAway = itemView.find<TextView>(R.id.tv_score_away)

        fun bind(item: EventMdl, listener: (EventMdl) -> Unit) {
            tvEventDate.text = item.date_event.convertDate()
            tvTeamHome.text = item.team_home
            tvTeamAway.text = item.team_away
            tvScoreHome.text = item.home_score.toString()
            tvScoreAway.text = item.away_score.toString()
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

}