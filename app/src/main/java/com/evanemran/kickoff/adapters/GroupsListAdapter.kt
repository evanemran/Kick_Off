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

class GroupsListAdapter(
    val context: Context,
    val list: List<StandingsResponse>,
    val listener: ClickListener<TeamInfo>
) : RecyclerView.Adapter<GroupsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsListViewHolder {
        return GroupsListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_group, parent, false))
    }

    override fun onBindViewHolder(holder: GroupsListViewHolder, position: Int) {
        val item = list[position]

        holder.textView_group_name.text = "Group "+item.group

        holder.recycler_group_items.setHasFixedSize(true)
        holder.recycler_group_items.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var adapter: GroupsItemAdapter = GroupsItemAdapter(context, item.teams)
        holder.recycler_group_items.adapter = adapter
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class GroupsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val recycler_group_items = itemView.findViewById<RecyclerView>(R.id.recycler_group_items)
    val textView_group_name = itemView.findViewById<TextView>(R.id.textView_group_name)
}