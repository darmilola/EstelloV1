package com.estello.android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.estello.android.ViewModel.DirectMessageModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DirectMessageAdapter extends RecyclerView.Adapter<DirectMessageAdapter.itemViewHolder> {

    ArrayList<DirectMessageModel> directMessageModelArrayList;

    public DirectMessageAdapter(ArrayList<DirectMessageModel> directMessageModels){
        this.directMessageModelArrayList = directMessageModels;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.direct_message_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return directMessageModelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{

        TextView sender_name,last_message,unread_count,timestamp;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
