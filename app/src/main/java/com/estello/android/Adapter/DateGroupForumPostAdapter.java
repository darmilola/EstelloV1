package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DateGroupForumPostAdapter extends RecyclerView.Adapter<DateGroupForumPostAdapter.viewHolder> {

    Context context;
    private HashMap<Integer, ArrayList<ForumPostModel>> forumPostPositionToListMap;
    private HashMap<Integer, String> forumPostPositionToDateMap;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    public DateGroupForumPostAdapter(Context context, HashMap<Integer, ArrayList<ForumPostModel>> forumPostPositionToListMap,HashMap<Integer, String> forumPostPositionToDateMap){

        this.context = context;
        this.forumPostPositionToListMap = forumPostPositionToListMap;
        this.forumPostPositionToDateMap = forumPostPositionToDateMap;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_date_item, parent, false);
        return new viewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.forumPostDate.setText(forumPostPositionToDateMap.get(position));



    }

    @Override
    public int getItemCount() {
        return forumPostPositionToListMap.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{


        TextView forumPostDate;

        public viewHolder(@NonNull View itemView) {

            super(itemView);
            forumPostDate = itemView.findViewById(R.id.forum_post_date);



        }
    }

}
