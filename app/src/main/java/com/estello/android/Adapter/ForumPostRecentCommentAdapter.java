package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.ViewModel.User;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForumPostRecentCommentAdapter extends RecyclerView.Adapter<ForumPostRecentCommentAdapter.viewHolder> {

    Context context;
    ArrayList<User> recentCommentList;

    public ForumPostRecentCommentAdapter(Context context, ArrayList<User> recentCommentList){

        this.context = context;
        this.recentCommentList = recentCommentList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_item_last_comments_recycler_item, parent, false);
        return new viewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return recentCommentList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{



        public viewHolder(@NonNull View itemView) {
            super(itemView);



        }
    }
}


