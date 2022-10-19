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
import com.evanemran.kickoff.models.MatchData
import com.evanemran.kickoff.models.MatchDataFly
import com.evanemran.kickoff.models.TeamInfo
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class MatchListAdapter(
    val context: Context,
    val list: List<MatchDataFly>,
    val listener: ClickListener<MatchDataFly>
) : RecyclerView.Adapter<MatchListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListViewHolder {
        return MatchListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_matches_two, parent, false))
    }

    override fun onBindViewHolder(holder: MatchListViewHolder, position: Int) {
        val item = list[position]

        holder.textView_homeName.text = item.home_team?.country
        holder.textView_awayName.text = item.away_team?.country

        val dateFormatter = SimpleDateFormat("EEE, d MMM")
        val timeFormatter = SimpleDateFormat("hh:mm")
        val ampmFormatter = SimpleDateFormat("a")
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(item.datetime)

        holder.match_date.text = dateFormatter.format(date)
        holder.match_time.text = timeFormatter.format(date)
        holder.match_time_ampm.text = ampmFormatter.format(date)

        Picasso.get().load("https://countryflagsapi.com/png/" + item.home_team?.name).placeholder(R.drawable.flag_placeholder).into(holder.imageView_homeImage)
        Picasso.get().load("https://countryflagsapi.com/png/" + item.away_team?.name).placeholder(R.drawable.flag_placeholder).into(holder.imageView_awayImage)

        holder.match_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class MatchListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val match_container = itemView.findViewById<CardView>(R.id.match_container)
    val imageView_homeImage = itemView.findViewById<ImageView>(R.id.imageView_homeImage)
    val imageView_awayImage = itemView.findViewById<ImageView>(R.id.imageView_awayImage)
    val textView_homeName = itemView.findViewById<TextView>(R.id.textView_homeName)
    val textView_awayName = itemView.findViewById<TextView>(R.id.textView_awayName)
    val match_date = itemView.findViewById<TextView>(R.id.match_date)
    val match_time = itemView.findViewById<TextView>(R.id.match_time)
    val match_time_ampm = itemView.findViewById<TextView>(R.id.match_time_ampm)
}