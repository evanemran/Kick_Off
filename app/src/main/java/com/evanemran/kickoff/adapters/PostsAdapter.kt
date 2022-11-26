package com.evanemran.kickoff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.evanemran.kickoff.R
import com.evanemran.kickoff.database.FirebaseDbConstants.TABLE_USER
import com.evanemran.kickoff.listeners.PostReactionListener
import com.evanemran.kickoff.models.PostData
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PostsAdapter(
    var mContext: Context,
    var mList: List<PostData>,
    var mListener: PostReactionListener<PostData>
) : RecyclerView.Adapter<PostsViewHolder>() {
    val context: Context = mContext
    val list: List<PostData> = mList
    var listener: PostReactionListener<PostData> = mListener
    var prettyTime: PrettyTime = PrettyTime(Locale.getDefault())
    var databaseReference: DatabaseReference? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_posts, parent, false))
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val data = list[position]

        try {
            getProfilePic(holder.imageView_user, data.postedBy.userId)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.textView_poster.text = data.postedBy.userName
        holder.textView_postBody.text = data.postBody
//        holder.textView_postTime.setText(getFormattedTime(data.getPosTTime()));
        //        holder.textView_postTime.setText(getFormattedTime(data.getPosTTime()));
        holder.textView_postTime.text = prettyTime.format(getDateFromStr(data.posTTime))

        holder.textView_likeCount.text = data.likes.toString() + ""
        holder.textView_commentCount.text = data.commentsCount.toString() + ""
        holder.textView_shareCount.text = data.shareCount.toString() + ""

        if (data.image.isEmpty() || data.image == null) {
            holder.imageView_post.visibility = View.GONE
        } else {
            holder.imageView_post.visibility = View.VISIBLE
            Picasso.get().load(data.image).placeholder(R.drawable.image_placeholder)
                .into(holder.imageView_post)
        }

        holder.button_like.setOnClickListener {
            listener.onLikeClicked(data)
            /*if (data.isLiked()){
                        data.setLiked(false);
                        holder.button_like.setImageResource(R.drawable.ic_like_fill);
                    }
                    else{
                        data.setLiked(true);
                        holder.button_like.setImageResource(R.drawable.ic_like);
                    }*/
        }
        holder.button_comment.setOnClickListener { listener.onCommentClicked(data) }
        holder.button_share.setOnClickListener { listener.onShareClicked(data) }

    }

    private fun getDateFromStr(posTTime: String): Date? {
        try {
            return SimpleDateFormat("EEE, d MMM yyyy HH:mm a").parse(posTTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    private fun getProfilePic(imageView: ImageView, uId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_USER)
        val dbQuery = databaseReference!!
            .orderByChild("userId")
            .equalTo(uId)
        dbQuery.addListenerForSingleValueEvent(
            databaseReference!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {
                        val image = postSnapshot.child("userPhoto").value.toString()
                        if (!image.isEmpty()) {
                            Picasso.get().load(image).into(imageView)
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView_poster = itemView.findViewById<TextView?>(R.id.textView_poster)
    val textView_postBody = itemView.findViewById<TextView?>(R.id.textView_postBody)
    val textView_postTime = itemView.findViewById<TextView?>(R.id.textView_postTime)
    val imageView_post = itemView.findViewById<ImageView?>(R.id.imageView_post)
    val button_share = itemView.findViewById<LinearLayout?>(R.id.button_share)
    val button_comment = itemView.findViewById<LinearLayout?>(R.id.button_comment)
    val button_like = itemView.findViewById<LinearLayout?>(R.id.button_like)
    val textView_likeCount = itemView.findViewById<TextView?>(R.id.textView_likeCount)
    val textView_commentCount = itemView.findViewById<TextView?>(R.id.textView_commentCount)
    val textView_shareCount = itemView.findViewById<TextView?>(R.id.textView_shareCount)
    val imageView_user = itemView.findViewById<ImageView?>(R.id.imageView_user)
}