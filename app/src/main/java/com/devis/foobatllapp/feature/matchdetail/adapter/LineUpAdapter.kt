package com.devis.foobatllapp.feature.matchdetail.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devis.foobatllapp.R
import com.devis.foobatllapp.core.model.LineUpMdl
import com.devis.foobatllapp.feature.matchdetail.ui.LineUpItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class LineUpAdapter(
    private val lineUpHome: List<LineUpMdl>,
    private val lineUpAway: List<LineUpMdl>
) : RecyclerView.Adapter<LineUpAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LineUpItemUI().createView(
            AnkoContext.Companion.create(parent.context, this)
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(lineUpHome[position], lineUpAway[position])
    }

    override fun getItemCount(): Int = lineUpHome.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvLineUpHome = itemView.find<TextView>(R.id.tv_line_up_home)
        private val tvLineUpAway = itemView.find<TextView>(R.id.tv_line_up_away)

        fun bind(itemHome: LineUpMdl, itemAway: LineUpMdl) {
            tvLineUpHome.text = String.format(
                itemView.resources.getString(R.string.placeholder_line_up),
                itemHome.position,
                itemHome.name
            )

            tvLineUpAway.text = String.format(
                itemView.resources.getString(R.string.placeholder_line_up),
                itemAway.name,
                itemAway.position
            )
        }
    }

}