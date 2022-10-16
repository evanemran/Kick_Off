package com.evanemran.kickoff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.kickoff.R
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.models.*
import com.squareup.picasso.Picasso

class StatesListAdapter(
    val context: Context,
    val list: List<StatesData>,
) : RecyclerView.Adapter<StatesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatesListViewHolder {
        return StatesListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_states, parent, false))
    }

    override fun onBindViewHolder(holder: StatesListViewHolder, position: Int) {
        val item = list[position]

        holder.textView_title.text = item.title
        holder.textView_value_one.text = item.value_one.toString()
        holder.textView_value_two.text = item.value_two.toString()

        var max: Int = 100
        max = if (item.value_one<item.value_two) {
            item.value_two
        } else item.value_one

        holder.progressbar_one.progress = item.value_one
        holder.progressbar_one.max = max
        holder.progressbar_two.progress = item.value_two
        holder.progressbar_two.max = max
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class StatesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView_title = itemView.findViewById<TextView>(R.id.textView_title)
    val textView_value_one = itemView.findViewById<TextView>(R.id.textView_value_one)
    val textView_value_two = itemView.findViewById<TextView>(R.id.textView_value_two)
    val progressbar_one = itemView.findViewById<ProgressBar>(R.id.progressbar_one)
    val progressbar_two = itemView.findViewById<ProgressBar>(R.id.progressbar_two)
}