package com.devis.foobatllapp.feature.match.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.core.util.convertDate
import com.devis.foobatllapp.feature.match.ui.NextMatchItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class NextMatchAdapter(
    private val listEvent: ArrayList<EventMdl>
) : RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = NextMatchItemUI().createView(
            AnkoContext.Companion.create(parent.context, this)
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvEventDate = itemView.find<TextView>(R.id.tv_match_date)
        private val tvTeamHome = itemView.find<TextView>(R.id.tv_team_home)
        private val tvTeamAway = itemView.find<TextView>(R.id.tv_team_away)
        private val tvMatchTime = itemView.find<TextView>(R.id.tv_match_time)

        fun bind(item: EventMdl) {
            tvEventDate.text = item.date_event.convertDate()
            tvTeamHome.text = item.team_home
            tvTeamAway.text = item.team_away
            tvMatchTime.text = item.strTime
        }
    }

}