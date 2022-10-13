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

class BlogAdapter(
    val context: Context,
    val list: List<Blog>,
    val listener: ClickListener<Blog>
) : RecyclerView.Adapter<BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(LayoutInflater.from(context).inflate(R.layout.list_blog, parent, false))
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val item = list[position]

        holder.textView_headline.text = item.title
        holder.textView_headline.isSelected = true
        holder.textView_description.text = item.description
        Picasso.get().load(item.urlToImage).into(holder.imageView_blog)

        holder.blog_container.setOnClickListener {
            listener.onClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView_blog = itemView.findViewById<ImageView>(R.id.imageView_blog)
    val textView_headline = itemView.findViewById<TextView>(R.id.textView_headline)
    val textView_description = itemView.findViewById<TextView>(R.id.textView_description)
    val blog_container = itemView.findViewById<CardView>(R.id.blog_container)
}