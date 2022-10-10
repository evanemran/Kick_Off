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
import com.evanemran.kickoff.models.TeamPoints
import com.squareup.picasso.Picasso

class GroupsItemAdapter(
    val context: Context,
    val list: List<TeamPoints>,
) : RecyclerView.Adapter<GroupsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsItemViewHolder {
        return GroupsItemViewHolder(LayoutInflater.from(context).inflate(R.layout.list_group_item, parent, false))
    }

    override fun onBindViewHolder(holder: GroupsItemViewHolder, position: Int) {
        val item = list[position]

        holder.textView_tTitle.text = item.name_en
        holder.textView_tTitle.isSelected = true

        if(item.name_en.equals("Nederland")) {
            Picasso.get().load("https://countryflagsapi.com/png/NLD").into(holder.imageView_tImage)
        }
        else if(item.name_en.equals("South Korea")) {
            Picasso.get().load("https://countryflagsapi.com/png/KOR").into(holder.imageView_tImage)
        }

        else Picasso.get().load("https://countryflagsapi.com/png/" + item.name_en).into(holder.imageView_tImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class GroupsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView_tImage = itemView.findViewById<ImageView>(R.id.imageView_tImage)
    val textView_tTitle = itemView.findViewById<TextView>(R.id.textView_tTitle)
}