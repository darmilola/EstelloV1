package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.R;
import com.estello.android.ViewModel.ChannelsModel;
import com.estello.android.ViewModel.CoursesModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExploreChannelAdapter extends RecyclerView.Adapter<ExploreChannelAdapter.itemViewHolder> {

    ArrayList<ChannelsModel> channelsModelArrayList;
    Context context;


    public ExploreChannelAdapter(ArrayList<ChannelsModel> channelsModelArrayList, Context context){
        this.channelsModelArrayList = channelsModelArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_channel_items, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return channelsModelArrayList.size();
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
