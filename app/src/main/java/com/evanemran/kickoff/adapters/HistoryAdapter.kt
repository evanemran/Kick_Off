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
import com.evanemran.kickoff.models.HistoryMenu
import com.evanemran.kickoff.models.TeamInfo
import com.evanemran.kickoff.models.TeamPoints
import com.evanemran.kickoff.models.WinnerData
import com.squareup.picasso.Picasso

class HistoryAdapter(
    val context: Context,
    val list: List<HistoryMenu>,
    val listener: ClickListener<HistoryMenu>
) : RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.list_history, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = list[position]

        holder.textView_history.text = item.title
        holder.imageView_history.setImageResource(item.icon)

        holder.history_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView_history = itemView.findViewById<ImageView>(R.id.imageView_history)
    val textView_history = itemView.findViewById<TextView>(R.id.textView_history)
    val history_container = itemView.findViewById<CardView>(R.id.history_container)
}