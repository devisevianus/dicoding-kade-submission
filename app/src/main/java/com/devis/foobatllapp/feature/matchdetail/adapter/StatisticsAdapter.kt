package com.devis.foobatllapp.feature.matchdetail.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.EventStatisticMdl
import com.devis.foobatllapp.feature.matchdetail.ui.StatisticsItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.findOptional

class StatisticsAdapter(
    private val listStatistic: List<EventStatisticMdl>
) : RecyclerView.Adapter<StatisticsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = StatisticsItemUI().createView(
            AnkoContext.Companion.create(parent.context, this)
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listStatistic.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listStatistic[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvStatisticName = itemView.findOptional<TextView>(R.id.tv_statistic_name)!!
        private val tvStatisticHome = itemView.findOptional<TextView>(R.id.tv_statistic_home)!!
        private val tvStatisticAway = itemView.findOptional<TextView>(R.id.tv_statistic_away)!!

        fun bind(item: EventStatisticMdl) {
            tvStatisticName.text = item.strStat
            tvStatisticHome.text = item.intHome
            tvStatisticAway.text = item.intAway
        }
    }

}