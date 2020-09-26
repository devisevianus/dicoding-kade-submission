package com.devis.foobatllapp.feature.main.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.LeagueMdl
import com.devis.foobatllapp.core.util.setImage
import com.devis.foobatllapp.feature.main.ui.LeagueItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by Devis on 20/09/20
 */

class LeagueAdapter(
    private val leagueList: List<LeagueMdl>,
    private val listener: (LeagueMdl) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LeagueItemUI().createView(AnkoContext.create(parent.context, this))
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = leagueList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(leagueList[position], listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivLeagueLogo: ImageView = itemView.find(R.id.iv_league_logo)
        private val tvLeagueName: TextView = itemView.find(R.id.tv_league_name)

        fun bind(item: LeagueMdl, listener: (LeagueMdl) -> Unit) {
            ivLeagueLogo.setImage(item.leagueLogo)
            tvLeagueName.text = item.leagueName
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    /*interface ItemClickListener {
        fun onItemClickListener(leagueName: String)
    }*/

}