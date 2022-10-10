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
import com.evanemran.kickoff.models.TeamInfo
import com.evanemran.kickoff.models.TeamPoints
import com.squareup.picasso.Picasso

class StandingItemAdapter(
    val context: Context,
    val list: List<TeamPoints>,
) : RecyclerView.Adapter<StandingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingItemViewHolder {
        return StandingItemViewHolder(LayoutInflater.from(context).inflate(R.layout.list_standings_item, parent, false))
    }

    override fun onBindViewHolder(holder: StandingItemViewHolder, position: Int) {
        val item = list[position]

        if (Integer.parseInt(item.team_id)%2!=0) {
            holder.standing_container.setBackgroundColor(context.getColor(R.color.gTrans))
        }
        holder.textView_name.text = item.name_en
        if (Integer.parseInt(item.team_id) < 10) {
            holder.textView_code.text = "0"+item.team_id
        }
        else holder.textView_code.text = item.team_id
        holder.textView_mp.text = item.mp
        holder.textView_w.text = item.w
        holder.textView_l.text = item.l
        holder.textView_p.text = item.pts
        holder.textView_gf.text = item.gf
        holder.textView_ga.text = item.ga
        holder.textView_gd.text = item.gd

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