package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.R;
import com.estello.android.ViewModel.CoursesModel;
import com.estello.android.ViewModel.PinnedItemsModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PinnedItemsAdapter extends RecyclerView.Adapter<PinnedItemsAdapter.itemViewHolder> {

    ArrayList<PinnedItemsModel> pinnedItemsModelArrayList;
    Context context;


    public PinnedItemsAdapter(ArrayList<PinnedItemsModel> pinnedItemsModelArrayList, Context context){
        this.pinnedItemsModelArrayList = pinnedItemsModelArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_details_pinned_recycler_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return pinnedItemsModelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{


        public itemViewHolder(View ItemView){
            super(ItemView);

        }

    }
}

