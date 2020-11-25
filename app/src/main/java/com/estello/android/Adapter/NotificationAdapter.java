package com.estello.android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.ViewModel.NotificationModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int typeMentions = 1;
    private static int typeReactions = 2;
    private static int typeBot = 3;
    ArrayList<NotificationModel> notificationModelArrayList;


    public NotificationAdapter(ArrayList<NotificationModel> NotificationModels){
        this.notificationModelArrayList = NotificationModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == typeMentions){

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_type_mentions, parent, false);
            return new MentionsNotificationViewHolder(view2);
        }
        else if(viewType == typeReactions){

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_type_reactions, parent, false);
            return new ReactionsNotificationViewHolder(view2);
        }
        else if(viewType == typeBot){

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_type_bots, parent, false);
            return new BotNotificationViewHolder(view2);
        }
        return  null;
    }
        @Override
        public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder,int position){

        }

        @Override
        public int getItemCount () {
            return notificationModelArrayList.size();
        }

      @Override
      public int getItemViewType(int position) {

        if(notificationModelArrayList.get(position).getNotificationType() == typeMentions){
            return typeMentions;
        }
        else if(notificationModelArrayList.get(position).getNotificationType() == typeReactions){
            return typeReactions;
        }
        else if(notificationModelArrayList.get(position).getNotificationType() == typeBot){
            return typeBot;
        }
        else {
            return typeBot;
        }
    }


        public class MentionsNotificationViewHolder extends RecyclerView.ViewHolder {


            public MentionsNotificationViewHolder(@NonNull View itemView) {
                super(itemView);

            }

        }
        public class ReactionsNotificationViewHolder extends RecyclerView.ViewHolder {


        public ReactionsNotificationViewHolder(@NonNull View itemView) {
            super(itemView);

        }

    }
       public class BotNotificationViewHolder extends RecyclerView.ViewHolder {


        public BotNotificationViewHolder(@NonNull View itemView) {
            super(itemView);

        }

    }
    }