package com.devis.foobatllapp.feature

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.LeagueMdl
import com.devis.foobatllapp.core.util.setImage
import kotlinx.android.synthetic.main.item_league.view.*

/**
 * Created by Devis on 20/09/20
 */

class LeagueAdapter(
    private val leagueList: List<LeagueMdl>,
    private val listener: (LeagueMdl) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = leagueList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(leagueList[position], listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: LeagueMdl, listener: (LeagueMdl) -> Unit) {
            itemView.iv_league_logo.setImage(
                BitmapDrawable(
                    itemView.resources,
                    BitmapFactory.decodeByteArray(item.leagueLogo, 0, item.leagueLogo.size)
                )
            )
            itemView.tv_league_name.text = item.leagueName
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClickListener(leagueName: String)
    }

}