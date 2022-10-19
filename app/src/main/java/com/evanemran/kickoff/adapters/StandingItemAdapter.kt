package com.evanemran.kickoff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.kickoff.R
import com.evanemran.kickoff.listeners.ClickListener
import com.evanemran.kickoff.models.FlyTeams
import com.evanemran.kickoff.models.TeamInfo
import com.evanemran.kickoff.models.TeamPoints
import com.squareup.picasso.Picasso

class StandingItemAdapter(
    val context: Context,
    val list: List<FlyTeams>,
) : RecyclerView.Adapter<StandingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingItemViewHolder {
        return StandingItemViewHolder(LayoutInflater.from(context).inflate(R.layout.list_standings_item, parent, false))
    }

    override fun onBindViewHolder(holder: StandingItemViewHolder, position: Int) {
        val item = list[position]

        if (position%2!=0) {
            holder.standing_container.setBackgroundColor(context.getColor(R.color.gTrans))
        }
        holder.textView_name.text = item.name
        holder.textView_code.text = (position+1).toString()

        holder.textView_mp.text = item.games_played
        holder.textView_w.text = item.wins
        holder.textView_l.text = item.losses
        holder.textView_p.text = item.group_points
        holder.textView_gf.text = item.goals_for
        holder.textView_ga.text = item.goals_against
        holder.textView_gd.text = item.goal_differential

        if(item.name.equals("Nederland")) {
            Picasso.get().load("https://countryflagsapi.com/png/NLD").into(holder.imageView_tImage)
        }
        else if(item.name.equals("Korea Republic")) {
            Picasso.get().load("https://countryflagsapi.com/png/KOR").into(holder.imageView_tImage)
        }

        else Picasso.get().load("https://countryflagsapi.com/png/" + item.name).into(holder.imageView_tImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class StandingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView_tImage = itemView.findViewById<ImageView>(R.id.imageView_tImage)
    val standing_container = itemView.findViewById<LinearLayout>(R.id.standing_container)
    val textView_code = itemView.findViewById<TextView>(R.id.textView_code)
    val textView_name = itemView.findViewById<TextView>(R.id.textView_name)
    val textView_mp = itemView.findViewById<TextView>(R.id.textView_mp)
    val textView_w = itemView.findViewById<TextView>(R.id.textView_w)
    val textView_l = itemView.findViewById<TextView>(R.id.textView_l)
    val textView_p = itemView.findViewById<TextView>(R.id.textView_p)
    val textView_gf = itemView.findViewById<TextView>(R.id.textView_gf)
    val textView_ga = itemView.findViewById<TextView>(R.id.textView_ga)
    val textView_gd = itemView.findViewById<TextView>(R.id.textView_gd)
}