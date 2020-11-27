package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.R;
import com.estello.android.ViewModel.CommunityViewHashtagsModel;
import com.estello.android.ViewModel.CoursesModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityViewHashtagsAdapter extends RecyclerView.Adapter<CommunityViewHashtagsAdapter.itemViewHolder> {

    List<Object> communityViewHashtagsModelArrayList;
    Context context;


    public CommunityViewHashtagsAdapter(List<Object> communityViewHashtagsModels, Context context){
        this.communityViewHashtagsModelArrayList   = communityViewHashtagsModels;
        this.context = context;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyclerview_type2_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return communityViewHashtagsModelArrayList.size();
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
