package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.R;
import com.estello.android.ViewModel.CommunityViewChannelsModel;
import com.estello.android.ViewModel.CoursesModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityViewChannelsDetailsAdapter extends RecyclerView.Adapter<CommunityViewChannelsDetailsAdapter.itemViewHolder> {

    List<Object> communityViewChannelsModels;
    Context context;


    public CommunityViewChannelsDetailsAdapter(List<Object> communityViewChannelsModels, Context context){
        this.communityViewChannelsModels = communityViewChannelsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_type1_recyclerview_item_type_details, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

        //CommunityViewChannelsModel channelsModel = (CommunityViewChannelsModel) communityViewChannelsModels.get(position);

    }

    @Override
    public int getItemCount() {
        return communityViewChannelsModels.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public itemViewHolder(View ItemView){
            super(ItemView);
        }
        @Override
        public void onClick(View view) {

        }
    }
}
