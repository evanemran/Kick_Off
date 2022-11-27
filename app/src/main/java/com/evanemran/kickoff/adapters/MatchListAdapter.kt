package com.evanemran.kickoff.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.kickoff.R
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.models.MatchDataFly
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class MatchListAdapter(
    val context: Context,
    val list: List<MatchDataFly>,
    val listener: ClickListener<MatchDataFly>
) : RecyclerView.Adapter<MatchListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListViewHolder {
        return MatchListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_matches_two, parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MatchListViewHolder, position: Int) {
        val item = list[position]

        holder.textView_homeName.text = item.home_team?.country
        holder.textView_awayName.text = item.away_team?.country

        holder.home_score.text = item.home_team?.goals.toString()
        holder.away_score.text = item.away_team?.goals.toString()

        val dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val dateF = DateTimeFormatter.ofPattern("EEE, d MMM", Locale.ENGLISH)
        val timeF = DateTimeFormatter.ofPattern("hh:mm", Locale.ENGLISH)
        val ampmF = DateTimeFormatter.ofPattern("a", Locale.ENGLISH)
        val formattedDate: String = LocalDateTime.parse(item.datetime, dFormatter)
            .atOffset(ZoneOffset.UTC)
            .atZoneSameInstant(ZoneId.systemDefault())
            .format(dateF)

        val formattedTime: String = LocalDateTime.parse(item.datetime, dFormatter)
            .atOffset(ZoneOffset.UTC)
            .atZoneSameInstant(ZoneId.systemDefault())
            .format(timeF)

        val formattedAmPm: String = LocalDateTime.parse(item.datetime, dFormatter)
            .atOffset(ZoneOffset.UTC)
            .atZoneSameInstant(ZoneId.systemDefault())
            .format(ampmF)

        holder.match_date.text = formattedDate
        holder.match_time.text = formattedTime
        holder.match_time_ampm.text = formattedAmPm

        Picasso.get().load("https://countryflagsapi.com/png/" + item.home_team?.name).placeholder(R.drawable.flag_placeholder).into(holder.imageView_homeImage)
        Picasso.get().load("https://countryflagsapi.com/png/" + item.away_team?.name).placeholder(R.drawable.flag_placeholder).into(holder.imageView_awayImage)

        holder.match_container.setOnClickListener {
            if(isFutureDate(item.datetime!!)) {
                Toast.makeText(context, "Match not started!", Toast.LENGTH_SHORT).show()
            }
            else{
                listener.onClicked(item)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFutureDate(dateStr: String): Boolean {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

        val formattedDate: String = LocalDateTime.parse(dateStr, dFormatter)
            .atOffset(ZoneOffset.UTC)
            .atZoneSameInstant(ZoneId.systemDefault())
            .format(dFormatter)

        val matchDate = dateFormatter.parse(formattedDate)
        return Date().before(matchDate)
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
    val home_score = itemView.findViewById<TextView>(R.id.home_score)
    val away_score = itemView.findViewById<TextView>(R.id.away_score)
}