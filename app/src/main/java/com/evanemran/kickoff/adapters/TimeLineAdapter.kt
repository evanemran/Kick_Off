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

class TimeLineAdapter(
    val context: Context,
    val list: List<TimelineData>,
) : RecyclerView.Adapter<TimeLineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
        return TimeLineViewHolder(LayoutInflater.from(context).inflate(R.layout.list_timeline, parent, false))
    }

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {
        val item = list[position]

        holder.textView_name.text = item.title
        holder.textView_info.text = item.subTitle
        holder.textView_time.text = item.time
        Picasso.get().load(item.image).into(holder.imageView_timeline)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class TimeLineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView_timeline = itemView.findViewById<ImageView>(R.id.imageView_timeline)
    val textView_name = itemView.findViewById<TextView>(R.id.textView_name)
    val textView_info = itemView.findViewById<TextView>(R.id.textView_info)
    val textView_time = itemView.findViewById<TextView>(R.id.textView_time)
}