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
import com.evanemran.kickoff.models.WinnerData
import com.evanemran.kickoff.models.WinnerDetails
import com.squareup.picasso.Picasso

class WinnerListAdapter(
    val context: Context,
    val list: List<WinnerDetails>,
) : RecyclerView.Adapter<WinnerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinnerListViewHolder {
        return WinnerListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_winners, parent, false))
    }

    override fun onBindViewHolder(holder: WinnerListViewHolder, position: Int) {
        val item = list[position]


        holder.textView_year.text = item.Year.toString()
        holder.textView_host.text = item.Country
        holder.textView_1st.text = item.Winner
        holder.textView_2nd.text = item.RunnersUp
        holder.textView_3rd.text = item.Third
        holder.textView_4th.text = item.Fourth

        holder.textView_1st.isSelected = true
        holder.textView_2nd.isSelected = true
        holder.textView_3rd.isSelected = true
        holder.textView_4th.isSelected = true
        holder.textView_host.isSelected = true

        Picasso.get().load("https://countryflagsapi.com/png/" + item.Winner).into(holder.imageView_1)
        Picasso.get().load("https://countryflagsapi.com/png/" + item.RunnersUp).into(holder.imageView_2)
        Picasso.get().load("https://countryflagsapi.com/png/" + item.Third).into(holder.imageView_3)
        Picasso.get().load("https://countryflagsapi.com/png/" + item.Fourth).into(holder.imageView_4)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class WinnerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView_year = itemView.findViewById<TextView>(R.id.textView_year)
    val textView_host = itemView.findViewById<TextView>(R.id.textView_host)
    val textView_1st = itemView.findViewById<TextView>(R.id.textView_1st)
    val textView_2nd = itemView.findViewById<TextView>(R.id.textView_2nd)
    val textView_3rd = itemView.findViewById<TextView>(R.id.textView_3rd)
    val textView_4th = itemView.findViewById<TextView>(R.id.textView_4th)

    val imageView_1 = itemView.findViewById<ImageView>(R.id.imageView_1)
    val imageView_2 = itemView.findViewById<ImageView>(R.id.imageView_2)
    val imageView_3 = itemView.findViewById<ImageView>(R.id.imageView_3)
    val imageView_4 = itemView.findViewById<ImageView>(R.id.imageView_4)
}