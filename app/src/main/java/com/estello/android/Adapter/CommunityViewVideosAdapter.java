package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityViewVideosAdapter extends RecyclerView.Adapter<CommunityViewVideosAdapter.itemViewHolder> {

    List<Object> communityViewVideoModelsArrayList;
    Context context;


    public CommunityViewVideosAdapter(List<Object> communityViewVideoModels, Context context){
        this.communityViewVideoModelsArrayList = communityViewVideoModels;
        this.context = context;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyclerview_type3_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return communityViewVideoModelsArrayList.size();
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
