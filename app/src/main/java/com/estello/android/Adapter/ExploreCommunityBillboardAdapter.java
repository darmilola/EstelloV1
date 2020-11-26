package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.estello.android.R;
import com.estello.android.ViewModel.ExploreCommuntyBillboardItem;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

public class ExploreCommunityBillboardAdapter extends RecyclerView.Adapter<ExploreCommunityBillboardAdapter.itemViewHolder> {

    ArrayList<ExploreCommuntyBillboardItem> exploreCommuntyBillboardItemArrayList;
    Context context;


    public ExploreCommunityBillboardAdapter(ArrayList<ExploreCommuntyBillboardItem> exploreCommuntyBillboardItems, Context context){
        this.exploreCommuntyBillboardItemArrayList = exploreCommuntyBillboardItems;
        this.context = context;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_billboard_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onViewAttachedToWindow(itemViewHolder viewHolder){

    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

        holder.imageView.setImageResource(exploreCommuntyBillboardItemArrayList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return exploreCommuntyBillboardItemArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        ImageView imageView;
        public itemViewHolder(View ItemView){
            super(ItemView);
            imageView = ItemView.findViewById(R.id.explore_community_billboard_item_imageview);
        }
        @Override
        public void onClick(View view) {

        }
    }
}
