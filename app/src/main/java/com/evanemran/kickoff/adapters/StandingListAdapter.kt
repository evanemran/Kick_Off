package com.evanemran.kickoff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.kickoff.R
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.models.StandingsResponse
import com.evanemran.kickoff.models.TeamInfo
import com.evanemran.kickoff.models.TeamPoints
import com.squareup.picasso.Picasso

class StandingListAdapter(
    val context: Context,
    val list: List<StandingsResponse>,
    val listener: ClickListener<TeamInfo>
) : RecyclerView.Adapter<StandingListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingListViewHolder {
        return StandingListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_standings, parent, false))
    }

    override fun onBindViewHolder(holder: StandingListViewHolder, position: Int) {
        val item = list[position]

        holder.textView_groupName.text = "Group "+item.group

        holder.recycler_standings_item.setHasFixedSize(true)
        holder.recycler_standings_item.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var adapter: StandingItemAdapter = StandingItemAdapter(context, item.teams)
        holder.recycler_standings_item.adapter = adapter
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class StandingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val recycler_standings_item = itemView.findViewById<RecyclerView>(R.id.recycler_standings_item)
    val textView_groupName = itemView.findViewById<TextView>(R.id.textView_groupName)
}