package com.devis.foobatllapp.feature.search.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.EventMdl
import com.devis.foobatllapp.core.util.convertDate
import com.devis.foobatllapp.feature.search.ui.SearchLastMatchItemUI
import com.devis.foobatllapp.feature.search.ui.SearchNextMatchItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class SearchMatchAdapter(
    private val listEvent: List<EventMdl>,
    private val listener: (EventMdl) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val typeLast = 1
    private val typeNext = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == typeLast) {
            ViewHolderLast(
                SearchLastMatchItemUI().createView(
                    AnkoContext.Companion.create(parent.context, this)
                )
            )
        } else {
            ViewHolderNext(
                SearchNextMatchItemUI().createView(
                    AnkoContext.Companion.create(parent.context, this)
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        if (holder is ViewHolderLast) {
            holder.bind(listEvent[position], listener)
        } else if (holder is ViewHolderNext) {
            holder.bind(listEvent[position], listener)
        }
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (listEvent[position].home_score == null
                || listEvent[position].away_score == null) {
            typeNext
        } else {
            typeLast
        }
    }

    inner class ViewHolderLast(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    inner class ViewHolderNext(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvEventDate = itemView.find<TextView>(R.id.tv_match_date)
        private val tvTeamHome = itemView.find<TextView>(R.id.tv_team_home)
        private val tvTeamAway = itemView.find<TextView>(R.id.tv_team_away)
        private val tvMatchTime = itemView.find<TextView>(R.id.tv_match_time)

        fun bind(item: EventMdl, listener: (EventMdl) -> Unit) {
            tvEventDate.text = item.date_event.convertDate()
            tvTeamHome.text = item.team_home
            tvTeamAway.text = item.team_away
            tvMatchTime.text = item.str_time

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

}