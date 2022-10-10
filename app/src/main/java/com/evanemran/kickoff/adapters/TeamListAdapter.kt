package com.evanemran.kickoff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.kickoff.R
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.models.TeamInfo
import com.squareup.picasso.Picasso

class TeamListAdapter(
    val context: Context,
    val list: List<TeamInfo>,
    val listener: ClickListener<TeamInfo>
) : RecyclerView.Adapter<TeamListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        return TeamListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_teams, parent, false))
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        val item = list[position]

        holder.textView_group_code.text = item.groups
        holder.textView_fifa_code.text = item.fifa_code
        holder.textView_team_name_en.text = item.name_en
        holder.textView_team_name_fa.text = item.name_fa

        Picasso.get().load("https://countryflagsapi.com/png/" + item.iso2).into(holder.imageView_teamFlag)

        holder.team_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class TeamListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val team_container = itemView.findViewById<CardView>(R.id.team_container)
    val imageView_teamFlag = itemView.findViewById<ImageView>(R.id.imageView_teamFlag)
    val textView_team_name_en = itemView.findViewById<TextView>(R.id.textView_team_name_en)
    val textView_team_name_fa = itemView.findViewById<TextView>(R.id.textView_team_name_fa)
    val textView_fifa_code = itemView.findViewById<TextView>(R.id.textView_fifa_code)
    val textView_group_code = itemView.findViewById<TextView>(R.id.textView_group_code)
}