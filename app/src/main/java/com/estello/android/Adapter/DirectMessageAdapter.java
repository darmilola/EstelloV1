package com.estello.android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.estello.android.ViewModel.DirectMessageModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DirectMessageAdapter extends RecyclerView.Adapter<DirectMessageAdapter.itemViewHolder> {

    ArrayList<DirectMessageModel> directMessageModelArrayList;
    ItemClickListener itemClickListener;

    public interface ItemClickListener{
        void  onProfilePictureClick();
        void onFullItemClick();
    }

    public DirectMessageAdapter(ArrayList<DirectMessageModel> directMessageModels,ItemClickListener itemClickListener){
        this.directMessageModelArrayList = directMessageModels;
        this.itemClickListener = itemClickListener;
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

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView userProfilePicture;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            userProfilePicture = itemView.findViewById(R.id.direct_message_sender_profile_image);

            userProfilePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onProfilePictureClick();
                }
            });

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onFullItemClick();
        }
    }
}
