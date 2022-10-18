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
import com.evanemran.kickoff.models.*
import com.squareup.picasso.Picasso

class WorldCupsAdapter(
    val context: Context,
    val list: List<WorldCups>,
    val listener: ClickListener<WorldCups>
) : RecyclerView.Adapter<WorldCupsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorldCupsViewHolder {
        return WorldCupsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_worldcups, parent, false))
    }

    override fun onBindViewHolder(holder: WorldCupsViewHolder, position: Int) {
        val item = list[position]

        holder.textView_worldcups.text = item.year.toString()
        Picasso.get().load(item.image).into(holder.imageView_worldcups)

        holder.history_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class WorldCupsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView_worldcups = itemView.findViewById<ImageView>(R.id.imageView_worldcups)
    val textView_worldcups = itemView.findViewById<TextView>(R.id.textView_worldcups)
    val history_container = itemView.findViewById<CardView>(R.id.history_container)
}