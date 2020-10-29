package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.R;
import com.estello.android.ViewModel.HashTagsSelectionModel;
import com.estello.android.ViewModel.MentionSelectionModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HashTagsSelectionAdapter extends RecyclerView.Adapter<HashTagsSelectionAdapter.itemViewHolder> {


    ArrayList<HashTagsSelectionModel> hashTagsSelectionModelArrayList;
    HashtagsItemClickListener hashtagsItemClickListener;
    Context context;

    public interface HashtagsItemClickListener {
        public void onHashTagItemClick();
    }

    public HashTagsSelectionAdapter(ArrayList<HashTagsSelectionModel> hashTagsSelectionModelArrayList, HashtagsItemClickListener hashtagsItemClickListener, Context context) {

        this.hashTagsSelectionModelArrayList = hashTagsSelectionModelArrayList;
        this.context = context;
        this.hashtagsItemClickListener = hashtagsItemClickListener;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.hashtags_selection_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return hashTagsSelectionModelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public itemViewHolder(View ItemView) {
            super(ItemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            hashtagsItemClickListener.onHashTagItemClick();
        }
    }
}
