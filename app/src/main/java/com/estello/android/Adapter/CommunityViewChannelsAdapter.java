package com.estello.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.JoinCommunityActivity;
import com.estello.android.R;
import com.estello.android.ViewModel.CommunityViewChannelsModel;
import com.estello.android.ViewModel.CoursesModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityViewChannelsAdapter extends RecyclerView.Adapter<CommunityViewChannelsAdapter.itemViewHolder> {

    List<Object> communityViewChannelsModels;
    Context context;


    public CommunityViewChannelsAdapter(List<Object> communityViewChannelsModels, Context context){
        this.communityViewChannelsModels = communityViewChannelsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyclerview_type1_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

       // CommunityViewChannelsModel channelsModel = (CommunityViewChannelsModel) communityViewChannelsModels.get(position);

    }

    @Override
    public int getItemCount() {
        return communityViewChannelsModels.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public itemViewHolder(View ItemView){

            super(ItemView);
            ItemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

            context.startActivity(new Intent(context, JoinCommunityActivity.class));

        }
    }
}
