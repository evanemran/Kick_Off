package com.evanemran.kickoff.listeners;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.evanemran.kickoff.models.PostData;
import com.evanemran.kickoff.utils.CommentDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class EventListeners {

    Context context;
    DatabaseReference databaseReference;
    FragmentManager manager;

    public EventListeners(Context context, DatabaseReference databaseReference, FragmentManager manager) {
        this.context = context;
        this.databaseReference = databaseReference;
        this.manager = manager;
    }

    public final PostReactionListener<PostData> reactionListener = new PostReactionListener<PostData>() {
        @Override
        public void onLikeClicked(PostData data) {
            HashMap post = new HashMap();
            post.put("likes", data.getLikes()+1);
            databaseReference.child(data.getPostId()).updateChildren(post).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        Toast.makeText(context, "Liked!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "Couldn't update!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onShareClicked(PostData data) {

        }

        @Override
        public void onCommentClicked(PostData data) {
            CommentDialog commentDialog = new CommentDialog(data, commentListener);
            commentDialog.show(manager, "comment");
        }
    };

    public final PostListener commentListener = new PostListener() {
        @Override
        public void onPostClicked(PostData data, Uri imageUri) {
            HashMap post = new HashMap();
            post.put("commentsCount", data.getCommentsCount()+1);
            databaseReference.child(data.getPostId()).updateChildren(post).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        Toast.makeText(context, "Commented!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "Couldn't comment!", Toast.LENGTH_SHORT).show();
                    }
                }
            });



            HashMap comment = new HashMap();
            comment.put("comments", data.getComments());
            databaseReference.child(data.getPostId()).updateChildren(comment).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        Toast.makeText(context, "Commented!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "Couldn't comment!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    };
}
