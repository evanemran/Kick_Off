package com.evanemran.kickoff.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evanemran.kickoff.R;
import com.evanemran.kickoff.adapters.CommentsAdapter;
import com.evanemran.kickoff.listeners.PostListener;
import com.evanemran.kickoff.models.CommentData;
import com.evanemran.kickoff.models.PostData;
import com.google.firebase.auth.FirebaseAuth;

public class CommentDialog extends DialogFragment {

    private PostListener listener;
    EditText editText_comment;
    ImageButton button_comment;
    RecyclerView recycler_comments;
    PostData postData;


    public CommentDialog(PostData postData, PostListener listener) {
        this.postData = postData;
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText_comment = view.findViewById(R.id.editText_comment);
        button_comment = view.findViewById(R.id.button_comment);
        recycler_comments = view.findViewById(R.id.recycler_comments);

        recycler_comments.setHasFixedSize(true);
        recycler_comments.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        CommentsAdapter adapter = new CommentsAdapter(getContext(), postData.getComments());
        recycler_comments.setAdapter(adapter);


        button_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = editText_comment.getText().toString();
                if (comment.isEmpty()){
                    editText_comment.setError("Write something.");
                }
                else{
                    CommentData commentData = new CommentData();
                    commentData.setCommentBody(comment);
                    commentData.setCommenter(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    postData.getComments().add(commentData);
                    listener.onPostClicked(postData, null);
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

}
